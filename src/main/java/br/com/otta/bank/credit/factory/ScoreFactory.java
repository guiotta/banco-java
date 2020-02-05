package br.com.otta.bank.credit.factory;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.otta.bank.credit.entity.Credit;
import br.com.otta.bank.credit.entity.Score;
import br.com.otta.bank.credit.model.ScoreData;

/**
 * Componente para criar {@link Score}, conforme um {@link ScoreData}.
 *
 * @author Guilherme
 *
 */
@Component
public class ScoreFactory {
    private final CreditFactory creditFactory;

    @Autowired
    public ScoreFactory(CreditFactory creditFactory) {
        this.creditFactory = creditFactory;
    }

    public Score create(ScoreData scoreData) {
        Optional<BigDecimal> optionalOverdraft = Optional.ofNullable(scoreData.getOverdraft());
        Optional<BigDecimal> optionalCreditCard = Optional.ofNullable(scoreData.getCreditCardLimit());
        Credit credit = creditFactory.create(optionalOverdraft, optionalCreditCard);

        return new Score(null, scoreData.getMinimal(), scoreData.getMaximal(), credit);
    }
}
