package br.com.otta.bank.credit.factory;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.otta.bank.credit.entity.CreditCard;

/**
 * Componente para criar um {@link CreditCard}.
 *
 * @author Guilherme
 *
 */
@Component
public class CreditCardFactory {

    public CreditCard create(Optional<BigDecimal> optionalCreditCardLimit) {
        if (optionalCreditCardLimit.isPresent()) {
            return new CreditCard(null, optionalCreditCardLimit.get());
        }
        return null;
    }
}
