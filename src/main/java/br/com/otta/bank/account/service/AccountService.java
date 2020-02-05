package br.com.otta.bank.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.otta.bank.account.builder.AccountBuilder;
import br.com.otta.bank.account.configuration.AccountConfiguration;
import br.com.otta.bank.account.entity.Account;
import br.com.otta.bank.account.entity.AccountType;
import br.com.otta.bank.account.factory.UniqueAccountNumberFactory;
import br.com.otta.bank.account.mapper.AccountTypeMapper;
import br.com.otta.bank.account.repository.AccountRepository;
import br.com.otta.bank.client.entity.Client;

/**
 * Classe de serviço, para conter a lógica com as operações das contas dos clientes.
 *
 * @author Guilherme
 *
 */
@Service
public class AccountService {
    private static final Object FLAG = new Object();

    /**
     * Esta dependência é configurável pelo arquivo de propriedades do projeto.
     */
    private final String agencyNumber;
    private final AccountRepository repository;
    private final UniqueAccountNumberFactory uniqueAccountNumberFactory;
    private final AccountTypeMapper accountTypeMapper;

    @Autowired
    public AccountService(@Qualifier(AccountConfiguration.AGENCY_BEAN_NAME) String agencyNumber,
            AccountRepository repository, UniqueAccountNumberFactory uniqueAccountNumberFactory,
            AccountTypeMapper accountTypeMapper) {
        super();
        this.agencyNumber = agencyNumber;
        this.repository = repository;
        this.uniqueAccountNumberFactory = uniqueAccountNumberFactory;
        this.accountTypeMapper = accountTypeMapper;
    }

    public void create(Client client) {
        AccountType type = accountTypeMapper.map(client.getType());

        // Bloco sincronizado para garantir que o valor de accountNumber recém criado, ainda será unico ao salvar na base. 
        synchronized (FLAG) {
            String accountNumber = uniqueAccountNumberFactory.get();
            Account account = new AccountBuilder()
                    .setNumber(accountNumber)
                    .setAgency(agencyNumber)
                    .setAccountType(type)
                    .setClient(client)
                    .build();

            account = repository.save(account);
        }
    }
}
