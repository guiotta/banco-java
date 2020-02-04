package br.com.otta.bank.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otta.bank.client.entity.Client;
import br.com.otta.bank.client.factory.ClientScoreFactory;
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
    private final ClientScoreFactory scoreFactory;

    @Autowired
    public ClientService(ClientRepository repository, DocumentValidatorManager validatorManager,
            ClientScoreFactory scoreFactory) {
        this.repository = repository;
        this.validatorManager = validatorManager;
        this.scoreFactory = scoreFactory;
    }

    public ClientInformation save(ClientData clientData) {
        int score = scoreFactory.get();
        ClientType type = ClientType.getClientType(clientData.getType());

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
