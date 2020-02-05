package br.com.otta.bank.account.builder;

import br.com.otta.bank.account.entity.Account;
import br.com.otta.bank.account.entity.AccountType;
import br.com.otta.bank.client.entity.Client;

public class AccountBuilder {
    private final Account account;

    public AccountBuilder() {
        this.account = new Account();
    }

    public AccountBuilder setNumber(String number) {
        this.account.setNumber(number);
        return this;
    }
    
    public AccountBuilder setAgency(String agency) {
        this.account.setAgency(agency);
        return this;
    }
    
    public AccountBuilder setAccountType(AccountType accountType) {
        this.account.setType(accountType);
        return this;
    }

    public AccountBuilder setClient(Client client) {
        this.account.setClient(client);
        return this;
    }

    public Account build() {
        return this.account;
    }
}
