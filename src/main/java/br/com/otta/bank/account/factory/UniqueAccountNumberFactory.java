package br.com.otta.bank.account.factory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.otta.bank.account.entity.Account;
import br.com.otta.bank.account.repository.AccountRepository;

/**
 * Componente para buscar um número de conta que ainda não tenha sido usado pelo sistema.
 *
 * @author Guilherme
 *
 */
@Component
public class UniqueAccountNumberFactory {
    private final AccountRepository repository;
    private final AccountNumberFactory accountNumberFactory;

    @Autowired
    public UniqueAccountNumberFactory(AccountRepository repository, AccountNumberFactory accountNumberFactory) {
        this.repository = repository;
        this.accountNumberFactory = accountNumberFactory;
    }

    public String get() {
        String accountNumber;
        do {
            accountNumber = accountNumberFactory.get();
        } while (this.verifyForAlreadyCreatedAccountNumber(accountNumber));

        return accountNumber;
    }

    private boolean verifyForAlreadyCreatedAccountNumber(String number) {
        Optional<Account> accountInDatabase = (number == null) ? Optional.empty() : repository.findByNumber(number);
        return accountInDatabase.isPresent();
    }
}
