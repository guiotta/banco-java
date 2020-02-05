package br.com.otta.bank.client.model;

import java.util.Objects;

/**
 * Classe do modelo para exibir as informações de um cliente.
 * 
 * @author Guilherme
 *
 */
public class ClientInformation {
    private final Long id;
    private final String name;
    private final String document;
    private final String type;
    private final int score;

    public ClientInformation(Long id, String name, String document, String type, int score) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.type = type;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public String getType() {
        return type;
    }

    public int getScore() {
        return score;
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
        if (!(obj instanceof ClientInformation)) {
            return false;
        }
        ClientInformation other = (ClientInformation) obj;
        return Objects.equals(document, other.document) && Objects.equals(id, other.id)
                && Objects.equals(name, other.name) && score == other.score && Objects.equals(type, other.type);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClientInformation [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", document=");
        builder.append(document);
        builder.append(", type=");
        builder.append(type);
        builder.append(", score=");
        builder.append(score);
        builder.append("]");
        return builder.toString();
    }
}
