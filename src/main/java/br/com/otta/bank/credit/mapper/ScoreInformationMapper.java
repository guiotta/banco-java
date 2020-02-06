package br.com.otta.bank.credit.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.otta.bank.credit.entity.Score;
import br.com.otta.bank.credit.extractor.LimitValuesExtractor;
import br.com.otta.bank.credit.model.LimitValues;
import br.com.otta.bank.credit.model.ScoreInformation;

/**
 * Componente para mapear as informações de {@link Score} em {@link ScoreInformation}.
 *
 * @author Guilherme
 *
 */
@Component
public class ScoreInformationMapper {
    private final LimitValuesExtractor extractor;

    @Autowired
    public ScoreInformationMapper(LimitValuesExtractor extractor) {
        this.extractor = extractor;
    }

    public ScoreInformation map(Score score) {
        LimitValues limitValues = extractor.extract(score);

        return new ScoreInformation(score.getId(), score.getMinimal(), score.getMaximal(),
                limitValues.getOverdraft(), limitValues.getCreditCardLimit());
    }
}
