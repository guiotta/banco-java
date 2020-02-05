package br.com.otta.bank.credit.model;

import java.util.Objects;

/**
 * Modelo para armazenar os valores de cheque especial e de limite do cartão de crédito para apresentação.
 *
 * @author Guilherme
 *
 */
public class LimitValues {
    private final String overdraft;
    private final String creditCardLimit;

    public LimitValues(String overdraft, String creditCardLimit) {
        this.overdraft = overdraft;
        this.creditCardLimit = creditCardLimit;
    }

    public String getOverdraft() {
        return overdraft;
    }

    public String getCreditCardLimit() {
        return creditCardLimit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardLimit, overdraft);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LimitValues)) {
            return false;
        }
        LimitValues other = (LimitValues) obj;
        return Objects.equals(creditCardLimit, other.creditCardLimit) && Objects.equals(overdraft, other.overdraft);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LimitValues [overdraft=");
        builder.append(overdraft);
        builder.append(", creditCardLimit=");
        builder.append(creditCardLimit);
        builder.append("]");
        return builder.toString();
    }
}
