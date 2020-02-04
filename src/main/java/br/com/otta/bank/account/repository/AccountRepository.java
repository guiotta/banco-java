package br.com.otta.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.otta.bank.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
