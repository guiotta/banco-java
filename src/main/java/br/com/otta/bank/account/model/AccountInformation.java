package br.com.otta.bank.account.model;

import java.util.Objects;

/**
 * Classe de modelo para apresentar as informações de uma conta.
 * @author Guilherme
 *
 */
public class AccountInformation {
    private final Long id;
    private final String clientName;
    private final String clientDocument;
    private final String accountType;
    private final String overdraft;
    private final String creditCardLimit;

    public AccountInformation(Long id, String clientName, String clientDocument, String accountType, String overdraft,
            String creditCardLimit) {
        this.id = id;
        this.clientName = clientName;
        this.clientDocument = clientDocument;
        this.accountType = accountType;
        this.overdraft = overdraft;
        this.creditCardLimit = creditCardLimit;
    }

    public Long getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientDocument() {
        return clientDocument;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getOverdraft() {
        return overdraft;
    }

    public String getCreditCardLimit() {
        return creditCardLimit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountType, clientDocument, clientName, creditCardLimit, id, overdraft);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccountInformation)) {
            return false;
        }
        AccountInformation other = (AccountInformation) obj;
        return Objects.equals(accountType, other.accountType) && Objects.equals(clientDocument, other.clientDocument)
                && Objects.equals(clientName, other.clientName)
                && Objects.equals(creditCardLimit, other.creditCardLimit) && Objects.equals(id, other.id)
                && Objects.equals(overdraft, other.overdraft);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AccountInformation [id=");
        builder.append(id);
        builder.append(", clientName=");
        builder.append(clientName);
        builder.append(", clientDocument=");
        builder.append(clientDocument);
        builder.append(", accountType=");
        builder.append(accountType);
        builder.append(", overdraft=");
        builder.append(overdraft);
        builder.append(", creditCardLimit=");
        builder.append(creditCardLimit);
        builder.append("]");
        return builder.toString();
    }
}
