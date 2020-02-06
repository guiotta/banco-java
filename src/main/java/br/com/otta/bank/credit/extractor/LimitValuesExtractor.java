package br.com.otta.bank.credit.extractor;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.otta.bank.credit.entity.Credit;
import br.com.otta.bank.credit.entity.CreditCard;
import br.com.otta.bank.credit.entity.Overdraft;
import br.com.otta.bank.credit.entity.Score;
import br.com.otta.bank.credit.model.LimitValues;

/**
 * Componente para extrar as informações de um {@link LimitValues} de um {@link Score}.
 *
 * @author Guilherme
 *
 */
@Component
public class LimitValuesExtractor {
    private static final String EMPTY_VALUES_LABEL = "Blocked";
    private static final String NUMBER_FORMAT = "R$ %.2f";

    public LimitValues extract(Score score) {
        Optional<Credit> optionalCredit = Optional.ofNullable(score.getCredit());
        String creditCardValue = EMPTY_VALUES_LABEL;
        String overdraftValue = EMPTY_VALUES_LABEL;

        if (optionalCredit.isPresent()) {
            Credit credit = optionalCredit.get();
            Optional<CreditCard> optionalCreditCard = Optional.ofNullable(credit.getCreditCard());
            Optional<Overdraft> optionalOverdraft = Optional.ofNullable(credit.getOverdraft());

            creditCardValue = optionalCreditCard.isPresent() ? String.format(NUMBER_FORMAT, optionalCreditCard.get().getValue()) : EMPTY_VALUES_LABEL;
            overdraftValue = optionalOverdraft.isPresent() ? String.format(NUMBER_FORMAT, optionalOverdraft.get().getValue()) : EMPTY_VALUES_LABEL;
        }
        return new LimitValues(overdraftValue, creditCardValue);
    }
}
