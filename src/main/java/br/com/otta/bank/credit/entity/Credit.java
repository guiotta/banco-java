package br.com.otta.bank.credit.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe para mapear a table credit da base de dados.
 *
 * @author Guilherme
 *
 */
@Entity
@Table(name = "credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "overdraft_id", unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    private Overdraft overdraft;
    @JoinColumn(name = "credit_card_id", unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    private CreditCard creditCard;

    public Credit() {
        // DO nothing.
    }

    public Credit(Long id, Overdraft overdraft, CreditCard creditCard) {
        this.id = id;
        this.overdraft = overdraft;
        this.creditCard = creditCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Overdraft getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(Overdraft overdraft) {
        this.overdraft = overdraft;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCard, id, overdraft);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Credit)) {
            return false;
        }
        Credit other = (Credit) obj;
        return Objects.equals(creditCard, other.creditCard) && Objects.equals(id, other.id)
                && Objects.equals(overdraft, other.overdraft);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Credit [id=");
        builder.append(id);
        builder.append(", overdraft=");
        builder.append(overdraft);
        builder.append(", creditCard=");
        builder.append(creditCard);
        builder.append("]");
        return builder.toString();
    }

}
