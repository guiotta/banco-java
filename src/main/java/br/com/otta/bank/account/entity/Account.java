package br.com.otta.bank.account.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.otta.bank.client.entity.Client;

/**
 * Classe para mapear a tabela ACCOUNT do H2.
 *
 * @author Guilherme
 *
 */
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number", length = 6)
    private String number;
    @Column(name = "agency", length = 32)
    private String agency;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AccountType type;
    @JoinColumn(name = "client_id", unique = true)
    @OneToOne
    private Client client;

    public Account() {
        // Do nothing.
    }

    public Account(Long id, String number, String agency, AccountType type, Client client) {
        this(number, agency, type);
        this.id = id;
        this.client = client;
    }

    public Account(String number, String agency, AccountType type) {
        this.number = number;
        this.agency = agency;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        return Objects.hash(agency, client, id, number, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        Account other = (Account) obj;
        return Objects.equals(agency, other.agency) && Objects.equals(client, other.client)
                && Objects.equals(id, other.id) && Objects.equals(number, other.number) && type == other.type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Account [id=");
        builder.append(id);
        builder.append(", number=");
        builder.append(number);
        builder.append(", agency=");
        builder.append(agency);
        builder.append(", type=");
        builder.append(type);
        builder.append(", client=");
        builder.append(client);
        builder.append("]");
        return builder.toString();
    }
}
