package br.com.otta.bank.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.otta.bank.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByNumber(String number);

}
