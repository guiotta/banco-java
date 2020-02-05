package br.com.otta.bank.account.mapper;

import org.springframework.stereotype.Component;

import br.com.otta.bank.account.entity.AccountType;
import br.com.otta.bank.client.model.ClientType;

/**
 * Componente para mapear um {@link ClientType} em um {@link AccountType}.
 *
 * @author Guilherme
 *
 */
@Component
public class AccountTypeMapper {

    public AccountType map(ClientType clientType) {
        switch (clientType) {
        case PHYSICAL:
            return AccountType.CHECKING_ACCOUNT;
        case LEGAL:
            return AccountType.BUSINESS_ACCOUNT;

        default:
            String message = String.format("Unknown ClientType: $s", clientType);
            throw new IllegalArgumentException(message);
        }
    }
}
