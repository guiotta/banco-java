package br.com.otta.bank.credit.factory;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.otta.bank.credit.entity.Credit;
import br.com.otta.bank.credit.entity.CreditCard;
import br.com.otta.bank.credit.entity.Overdraft;

/**
 * Componente para criar um {@link Credit}.
 *
 * @author Guilherme
 *
 */
@Component
public class CreditFactory {
    private final OverdraftFactory overdraftFactory;
    private final CreditCardFactory creditCardFactory;

    @Autowired
    public CreditFactory(OverdraftFactory overdraftFactory, CreditCardFactory creditCardFactory) {
        this.overdraftFactory = overdraftFactory;
        this.creditCardFactory = creditCardFactory;
    }

    public Credit create(Optional<BigDecimal> optionalOverdraftValue, Optional<BigDecimal> optionalCreditCardLimit) {
        if (optionalOverdraftValue.isPresent() || optionalCreditCardLimit.isPresent()) {
            Overdraft overdraft = overdraftFactory.create(optionalOverdraftValue);
            CreditCard creditCard = creditCardFactory.create(optionalCreditCardLimit);

            return new Credit(null, overdraft, creditCard);
        }
        return null;
    }
}
