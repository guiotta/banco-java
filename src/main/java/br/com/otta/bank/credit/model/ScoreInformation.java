package br.com.otta.bank.credit.model;

import java.util.Objects;

public class ScoreInformation {
    private Long id;
    private int minimal;
    private int maximal;
    private String overdraft;
    private String creditCardLimit; 

    public ScoreInformation() {
        // Do nothing.
    }

    public ScoreInformation(Long id, int minimal, int maximal, String overdraft, String creditCardLimit) {
        this.id = id;
        this.minimal = minimal;
        this.maximal = maximal;
        this.overdraft = overdraft;
        this.creditCardLimit = creditCardLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMinimal() {
        return minimal;
    }

    public void setMinimal(int minimal) {
        this.minimal = minimal;
    }

    public int getMaximal() {
        return maximal;
    }

    public void setMaximal(int maximal) {
        this.maximal = maximal;
    }

    public String getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(String overdraft) {
        this.overdraft = overdraft;
    }

    public String getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(String creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardLimit, id, maximal, minimal, overdraft);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScoreInformation)) {
            return false;
        }
        ScoreInformation other = (ScoreInformation) obj;
        return Objects.equals(creditCardLimit, other.creditCardLimit) && Objects.equals(id, other.id)
                && maximal == other.maximal && minimal == other.minimal && Objects.equals(overdraft, other.overdraft);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ScoreInformation [id=");
        builder.append(id);
        builder.append(", minimal=");
        builder.append(minimal);
        builder.append(", maximal=");
        builder.append(maximal);
        builder.append(", overdraft=");
        builder.append(overdraft);
        builder.append(", creditCardLimit=");
        builder.append(creditCardLimit);
        builder.append("]");
        return builder.toString();
    }
}
