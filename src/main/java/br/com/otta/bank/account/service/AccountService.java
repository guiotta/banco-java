package br.com.otta.bank.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.otta.bank.account.configuration.AccountConfiguration;
import br.com.otta.bank.account.entity.Account;
import br.com.otta.bank.account.entity.AccountType;
import br.com.otta.bank.account.factory.AccountNumberFactory;
import br.com.otta.bank.account.repository.AccountRepository;
import br.com.otta.bank.client.entity.Client;
import br.com.otta.bank.client.model.ClientType;

/**
 * Classe de serviço, para conter a lógica com as operações das contas dos clientes.
 *
 * @author Guilherme
 *
 */
@Service
public class AccountService {
    /**
     * Esta dependência é configurável pelo arquivo de propriedades do projeto.
     */
    private final String agencyNumber;
    private final AccountRepository repository;
    private final AccountNumberFactory accountNumberFactory;

    @Autowired
    public AccountService(@Qualifier(AccountConfiguration.AGENCY_BEAN_NAME) String agencyNumber,
            AccountRepository repository, AccountNumberFactory accountNumberFactory) {
        this.agencyNumber = agencyNumber;
        this.repository = repository;
        this.accountNumberFactory = accountNumberFactory;
    }

    public void create(Client client) {
        AccountType type = client.getType() == ClientType.PHYSICAL ? AccountType.CHECKING_ACCOUNT : AccountType.BUSINESS_ACCOUNT;
        String accountNumber = accountNumberFactory.get();
        Account account = new Account();
        account.setNumber(accountNumber);
        account.setAgency(agencyNumber);
        account.setType(type);
        account.setClient(client);

        account = repository.save(account);
    }
}
