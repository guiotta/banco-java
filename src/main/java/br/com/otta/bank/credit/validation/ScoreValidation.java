package br.com.otta.bank.credit.validation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.otta.bank.credit.model.ScoreData;
import br.com.otta.bank.credit.validation.api.ScoreRulesValidation;
import br.com.otta.bank.credit.validation.api.exception.ScoreValidationException;

/**
 * Componente para realizar as validações de um {@link ScoreData}, antes de alterar os seus valores.
 * @author Guilherme
 *
 */
@Component
public class ScoreValidation {
    private final Collection<ScoreRulesValidation> scoreValidators;

    @Autowired
    public ScoreValidation(Collection<ScoreRulesValidation> scoreValidators) {
        this.scoreValidators = scoreValidators;
    }

    public boolean validate(ScoreData scoreData) {
        List<String> validationErrors = scoreValidators.stream()
                .map(validator -> validator.validate(scoreData))
                .filter(result -> result.isPresent())
                .map(result -> result.get())
                .collect(Collectors.toList());

        if (!validationErrors.isEmpty()) {
            throw new ScoreValidationException(validationErrors);
        }

        return true;
    }

}
