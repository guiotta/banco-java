package br.com.otta.bank.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.otta.bank.client.entity.Client;
import br.com.otta.bank.client.factory.ClientScoreFactory;
import br.com.otta.bank.client.model.ClientData;
import br.com.otta.bank.client.model.ClientInformation;
import br.com.otta.bank.client.model.ClientType;
import br.com.otta.bank.client.repository.ClientRepository;
import br.com.otta.bank.client.validation.ValidationService;

@Service
public class ClientService {
    private final ClientRepository repository;
    private final ValidationService validationService;
    private final ClientScoreFactory scoreFactory;

    @Autowired
    public ClientService(ClientRepository repository, ValidationService validationService,
            ClientScoreFactory scoreFactory) {
        this.repository = repository;
        this.validationService = validationService;
        this.scoreFactory = scoreFactory;
    }

    public ClientInformation save(ClientData clientData) {
        int score = scoreFactory.get();
        ClientType type = ClientType.getClientType(clientData.getType());

        validationService.validateClientDocument(type, clientData.getDocument());

        Client client = new Client(clientData.getName(), type, clientData.getDocument(), score);
        client = this.save(client);
        return new ClientInformation(client.getId(), client.getDocument(), client.getType(), client.getScore());
    }

    /**
     * Salva um {@link Client} e trata casos de violação de campos únicos.
     */
    private Client save(Client client) {
        try {
            return repository.save(client);
        } catch (DataIntegrityViolationException e) {
            String message = String.format("Client with non unique attributes: %s.", client);
            throw new IllegalArgumentException(message);
        }
    }
}
