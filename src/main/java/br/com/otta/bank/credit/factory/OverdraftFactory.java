package br.com.otta.bank.credit.factory;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.otta.bank.credit.entity.Overdraft;

/**
 * Componente para criar um {@link Overdraft}.
 * @author Guilherme
 *
 */
@Component
public class OverdraftFactory {

    public Overdraft create(Optional<BigDecimal> optionalOverdraftValue) {
        if (optionalOverdraftValue.isPresent()) {
            return new Overdraft(null, optionalOverdraftValue.get());
        }
        return null;
    }
}
