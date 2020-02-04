package br.com.otta.bank.client.validation.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Classe de modelo para o retorno de informações da validação.
 *
 * @author Guilherme
 *
 */
public class ValidationInformation {
    private final Collection<String> messages;

    public ValidationInformation() {
        this.messages = new ArrayList<String>();
    }

    public boolean isValid() {
        return this.messages.isEmpty();
    }

    public Collection<String> getMessages() {
        return this.messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ValidationInformation)) {
            return false;
        }
        ValidationInformation other = (ValidationInformation) obj;
        return Objects.equals(messages, other.messages);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ValidationInformation [messages=");
        builder.append(messages);
        builder.append("]");
        return builder.toString();
    }
}
