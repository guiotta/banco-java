package br.com.otta.bank.client.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otta.bank.client.entity.Client;
import br.com.otta.bank.client.model.ClientData;
import br.com.otta.bank.client.model.ClientInformation;
import br.com.otta.bank.client.model.ClientType;
import br.com.otta.bank.client.repository.ClientRepository;
import br.com.otta.bank.client.validation.api.DocumentValidator;
import br.com.otta.bank.client.validation.exception.ValidationFailedException;
import br.com.otta.bank.client.validation.manager.DocumentValidatorManager;
import br.com.otta.bank.client.validation.model.ValidationInformation;

@Service
public class ClientService {
    private final ClientRepository repository;
    private final DocumentValidatorManager validatorManager;

    @Autowired
    public ClientService(ClientRepository repository, DocumentValidatorManager validatorManager) {
        this.repository = repository;
        this.validatorManager = validatorManager;
    }

    public ClientInformation save(ClientData clientData) {
        // TODO: Componentizar lógica para gerar um Score.
        Random random = new Random();
        int score = random.nextInt(10);

        ClientType type = clientData.getType() == 0 ? ClientType.PHYSICAL : ClientType.LEGAL;
        // TODO: Componentizar a lógica do validador para um Service.
        DocumentValidator validator = validatorManager.get(type);
        ValidationInformation validateInformation = validator.validate(clientData.getDocument());
        if (!validateInformation.isValid()) {
            throw new ValidationFailedException(validateInformation.getMessages());
        }

        Client client = new Client(clientData.getName(), type, clientData.getDocument(), score);
        client = repository.save(client);
        return new ClientInformation(client.getId(), client.getDocument(), client.getType(), client.getScore());
    }
}
