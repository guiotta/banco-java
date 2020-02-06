package br.com.otta.bank.credit.validation.api.exception;

import java.util.Collection;

/**
 * Exceção para ser lançada após falhas na validação dos Scores.
 *
 * @author Guilherme
 *
 */
public class ScoreValidationException extends RuntimeException {
    private static final long serialVersionUID = 8334276909413208566L;
    private final Collection<String> messages;

    public ScoreValidationException(Collection<String> messages) {
        this.messages = messages;
    }

    public Collection<String> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ScoreValidationException [messages=");
        builder.append(messages);
        builder.append("]");
        return builder.toString();
    }

}
