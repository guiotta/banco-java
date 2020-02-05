package br.com.otta.bank.account.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.otta.bank.account.builder.AccountBuilder;
import br.com.otta.bank.account.configuration.AccountConfiguration;
import br.com.otta.bank.account.entity.Account;
import br.com.otta.bank.account.entity.AccountType;
import br.com.otta.bank.account.factory.UniqueAccountNumberFactory;
import br.com.otta.bank.account.mapper.AccountInformationMapper;
import br.com.otta.bank.account.mapper.AccountTypeMapper;
import br.com.otta.bank.account.model.AccountInformation;
import br.com.otta.bank.account.repository.AccountRepository;
import br.com.otta.bank.client.entity.Client;
import br.com.otta.bank.credit.entity.Score;
import br.com.otta.bank.credit.filter.ScoreRangeFilter;
import br.com.otta.bank.credit.repository.ScoreRepository;

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
    private final AccountRepository accountRepository;
    private final UniqueAccountNumberFactory uniqueAccountNumberFactory;
    private final AccountTypeMapper accountTypeMapper;
    private final ScoreRepository scoreRepository;
    private final ScoreRangeFilter scoreRangeFilter;
    private final AccountInformationMapper accountInformationMapper;

    @Autowired
    public AccountService(@Qualifier(AccountConfiguration.AGENCY_BEAN_NAME) String agencyNumber,
            AccountRepository accountRepository, UniqueAccountNumberFactory uniqueAccountNumberFactory,
            AccountTypeMapper accountTypeMapper, ScoreRepository scoreRepository, ScoreRangeFilter scoreRangeFilter,
            AccountInformationMapper accountInformationMapper) {
        this.agencyNumber = agencyNumber;
        this.accountRepository = accountRepository;
        this.uniqueAccountNumberFactory = uniqueAccountNumberFactory;
        this.accountTypeMapper = accountTypeMapper;
        this.scoreRepository = scoreRepository;
        this.scoreRangeFilter = scoreRangeFilter;
        this.accountInformationMapper = accountInformationMapper;
    }

    public Account create(Client client) {
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

            account = accountRepository.save(account);

            return account;
        }
    }

    public Collection<AccountInformation> findAll() {
        List<Score> scores = scoreRepository.findAll();
        return accountRepository.findAll().stream().map(account -> {
            Score filteredScore = scores.stream()
                    .filter(score -> scoreRangeFilter.filter(account.getClient().getScore(), score))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Could not find any Score to Account. " + account.toString()));
            return accountInformationMapper.map(account, filteredScore);
        }).collect(Collectors.toList());
    }
}
