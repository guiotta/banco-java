package br.com.otta.bank.client.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.otta.bank.account.service.AccountService;
import br.com.otta.bank.client.entity.Client;
import br.com.otta.bank.client.factory.ClientScoreFactory;
import br.com.otta.bank.client.mapper.ClientInformationMapper;
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
    private final AccountService accountService;
    private final ClientInformationMapper mapper;

    @Autowired
    public ClientService(ClientRepository repository, ValidationService validationService,
            ClientScoreFactory scoreFactory, AccountService accountService, ClientInformationMapper mapper) {
        this.repository = repository;
        this.validationService = validationService;
        this.scoreFactory = scoreFactory;
        this.accountService = accountService;
        this.mapper = mapper;
    }

    public ClientInformation save(ClientData clientData) {
        int score = scoreFactory.get();
        ClientType type = ClientType.getClientType(clientData.getType());

        validationService.validateClientDocument(type, clientData.getDocument());

        Client client = new Client(clientData.getName(), type, clientData.getDocument(), score);
        client = this.save(client);
        accountService.create(client);

        return mapper.map(client);
    }

    public Collection<ClientInformation> findAll() {
        return repository.findAll().stream().map(client -> mapper.map(client)).collect(Collectors.toList());
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
