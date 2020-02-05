package br.com.otta.bank.credit.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe para mapear a table score da base de dados.
 *
 * @author Guilherme
 *
 */
@Entity
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "minimal", unique = true)
    private int minimal;
    @Column(name = "maximal", unique = true)
    private int maximal;
    @JoinColumn(name = "credit_id", unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    private Credit credit;

    public Score() {
        // Do nothing.
    }

    public Score(Long id, int minimal, int maximal, Credit credit) {
        this.id = id;
        this.minimal = minimal;
        this.maximal = maximal;
        this.credit = credit;
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

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(credit, id, maximal, minimal);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Score)) {
            return false;
        }
        Score other = (Score) obj;
        return Objects.equals(credit, other.credit) && Objects.equals(id, other.id) && maximal == other.maximal
                && minimal == other.minimal;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Score [id=");
        builder.append(id);
        builder.append(", minimal=");
        builder.append(minimal);
        builder.append(", maximal=");
        builder.append(maximal);
        builder.append(", credit=");
        builder.append(credit);
        builder.append("]");
        return builder.toString();
    }

}
