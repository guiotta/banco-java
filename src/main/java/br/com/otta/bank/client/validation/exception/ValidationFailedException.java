package br.com.otta.bank.client.validation.exception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Exceção criada para casos em que a validação falhou.
 * @author Guilherme
 *
 */
public class ValidationFailedException extends RuntimeException {
    private static final long serialVersionUID = -8380110776695903348L;

    private final Collection<String> messages;

    public ValidationFailedException() {
        this.messages = new ArrayList<>();
    }

    public ValidationFailedException(Collection<String> messages) {
        this.messages = messages;
    }

    public Collection<String> getMessages() {
        return messages;
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
        if (!(obj instanceof ValidationFailedException)) {
            return false;
        }
        ValidationFailedException other = (ValidationFailedException) obj;
        return Objects.equals(messages, other.messages);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ValidationFailedException [messages=");
        builder.append(messages);
        builder.append("]");
        return builder.toString();
    }

}
