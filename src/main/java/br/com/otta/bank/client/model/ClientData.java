package br.com.otta.bank.client.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe modelo para entrada de dados dos clientes na API.
 * 
 * @author Guilherme
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientData {
    private Long id;
    private String document;
    private int type;

    public ClientData() {
        // Do nothing.
    }

    public ClientData(Long id, String document, int type) {
        this(document, type);
        this.id = id;
    }

    public ClientData(String document, int type) {
        this.document = document;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(document, id, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClientData)) {
            return false;
        }
        ClientData other = (ClientData) obj;
        return Objects.equals(document, other.document) && Objects.equals(id, other.id) && type == other.type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClientData [id=");
        builder.append(id);
        builder.append(", document=");
        builder.append(document);
        builder.append(", type=");
        builder.append(type);
        builder.append("]");
        return builder.toString();
    }

}
