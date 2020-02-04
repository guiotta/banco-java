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
    private final String document;
    private final ClientType type;
    private final int score;

    public ClientInformation(Long id, String document, ClientType type, int score) {
        this.id = id;
        this.document = document;
        this.type = type;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public String getDocument() {
        return document;
    }

    public ClientType getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(document, id, score, type);
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
        return Objects.equals(document, other.document) && Objects.equals(id, other.id) && score == other.score
                && type == other.type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClientInformation [id=");
        builder.append(id);
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
