package br.com.otta.bank.client.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.otta.bank.client.model.ClientType;

/**
 * Classe para mapear a tabela CLIENT do H2.
 *
 * @author Guilherme
 *
 */
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 64)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ClientType type;
    @Column(name = "document", length = 14)
    private String document;
    @Column(name = "score")
    private int score;

    public Client() {
        // Do nothing.
    }

    public Client(String name, ClientType type, String document, int score) {
        this.name = name;
        this.type = type;
        this.document = document;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(document, id, name, score, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Client)) {
            return false;
        }
        Client other = (Client) obj;
        return Objects.equals(document, other.document) && Objects.equals(id, other.id)
                && Objects.equals(name, other.name) && score == other.score && type == other.type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Client [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", type=");
        builder.append(type);
        builder.append(", document=");
        builder.append(document);
        builder.append(", score=");
        builder.append(score);
        builder.append("]");
        return builder.toString();
    }
}
