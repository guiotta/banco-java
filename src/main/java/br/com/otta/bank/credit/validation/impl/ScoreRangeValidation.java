package br.com.otta.bank.credit.validation.impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.otta.bank.credit.entity.Score;
import br.com.otta.bank.credit.model.ScoreData;
import br.com.otta.bank.credit.repository.ScoreRepository;
import br.com.otta.bank.credit.validation.api.ScoreRulesValidation;

/**
 * Componente para verificar se o Range usado por um {@link Score} ainda está vago.
 * @author Guilherme
 *
 */
@Component
public class ScoreRangeValidation implements ScoreRulesValidation {
    private ScoreRepository repository;

    @Autowired
    public ScoreRangeValidation(ScoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<String> validate(ScoreData score) {
        // Busca os Scores em que o paramêtro estiver entre o valor minímo e o máximo.
        Collection<Score> scoresByMinimal = repository.findByScoreRange(score.getMinimal());
        Collection<Score> scoresByMaximal = repository.findByScoreRange(score.getMaximal());

        // Se houver um Score e o mesmo não tiver o mesmo Id do Score de paramêtro, deve barrar.
        if(isCollectionOfDifferentScores(scoresByMinimal, score) || isCollectionOfDifferentScores(scoresByMaximal, score)) {
            return Optional.of(String.format("Range of Score already in use: %d - %d.", score.getMinimal(), score.getMaximal()));
        }

        return Optional.empty();
    }

    private boolean isCollectionOfDifferentScores(Collection<Score> scores, ScoreData score) {
        return !scores.isEmpty()
                && scores.stream().anyMatch(scoreByMinimal -> scoreByMinimal.getId() != score.getId());
    }

}
