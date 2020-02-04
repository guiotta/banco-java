package br.com.otta.bank.client.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otta.bank.client.entity.Client;
import br.com.otta.bank.client.model.ClientData;
import br.com.otta.bank.client.model.ClientInformation;
import br.com.otta.bank.client.model.ClientType;
import br.com.otta.bank.client.repository.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public ClientInformation save(ClientData clientData) {
        // TODO: Componentizar lógica para gerar um Score.
        Random random = new Random();
        int score = random.nextInt(10);

        ClientType type = clientData.getType() == 0 ? ClientType.PHYSICAL : ClientType.LEGAL;
        Client client = new Client(type, clientData.getDocument(), score);

        client = repository.save(client);
        return new ClientInformation(client.getId(), client.getDocument(), client.getType(), client.getScore());
    }
}
