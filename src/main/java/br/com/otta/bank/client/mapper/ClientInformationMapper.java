package br.com.otta.bank.client.mapper;

import org.springframework.stereotype.Component;

import br.com.otta.bank.client.entity.Client;
import br.com.otta.bank.client.model.ClientInformation;

/**
 * Componente para mapear um {@link Client} em um {@link ClientInformation}.
 *
 * @author Guilherme
 *
 */
@Component
public class ClientInformationMapper {
    public ClientInformation map(Client client) {
        return new ClientInformation(client.getId(), client.getName(), client.getDocument(),
                client.getType().getLabel(), client.getScore());
    }
}
