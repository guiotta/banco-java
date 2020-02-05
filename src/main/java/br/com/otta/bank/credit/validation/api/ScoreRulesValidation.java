package br.com.otta.bank.credit.validation.api;

import java.util.Optional;

import br.com.otta.bank.credit.model.ScoreData;

public interface ScoreRulesValidation {
    Optional<String> validate(ScoreData score);
}
