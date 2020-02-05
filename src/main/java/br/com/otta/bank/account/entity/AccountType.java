package br.com.otta.bank.account.entity;

/**
 * Enum para os tipos de conta possíveis.
 * 
 * @author Guilherme
 *
 */
public enum AccountType {
    CHECKING_ACCOUNT("C"),
    BUSINESS_ACCOUNT("E");

    private String id;

    private AccountType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
