package br.com.otta.bank.credit.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * Classe de modelo para ser utilizada ao alterar as informações de Crédito de um Score.
 *
 * @author Guilherme
 *
 */
public class ScoreData {
    private Long id;
    @NotNull
    private Integer minimal;
    @NotNull
    private Integer maximal;
    private BigDecimal overdraft;
    private BigDecimal creditCardLimit;

    public ScoreData() {
        // Do nothing.
    }

    public ScoreData(Long id, int minimal, int maximal, BigDecimal overdraft, BigDecimal creditCardLimit) {
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

    public Integer getMinimal() {
        return minimal;
    }

    public void setMinimal(Integer minimal) {
        this.minimal = minimal;
    }

    public Integer getMaximal() {
        return maximal;
    }

    public void setMaximal(Integer maximal) {
        this.maximal = maximal;
    }

    public BigDecimal getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(BigDecimal overdraft) {
        this.overdraft = overdraft;
    }

    public BigDecimal getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(BigDecimal creditCardLimit) {
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
        if (!(obj instanceof ScoreData)) {
            return false;
        }
        ScoreData other = (ScoreData) obj;
        return Objects.equals(creditCardLimit, other.creditCardLimit) && Objects.equals(id, other.id)
                && maximal == other.maximal && minimal == other.minimal && Objects.equals(overdraft, other.overdraft);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ScoreData [id=");
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
