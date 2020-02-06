package br.com.otta.bank.credit.factory;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.otta.bank.credit.entity.CreditCard;

/**
 * Testes unitários do componente {@link CreditCardFactory}.
 *
 * @author Guilherme
 *
 */
public class CreditCardFactoryTest {
    private static final BigDecimal VALUE = BigDecimal.ONE;

    private CreditCardFactory factory;

    @BeforeEach
    protected void setUp() throws Exception {
        this.factory = new CreditCardFactory();
    }

    @Test
    public void shouldCorrectlyBuildCreditCard() throws Exception {
        // given
        CreditCard expectedValue = new CreditCard(null, VALUE);
        // when
        CreditCard actualValue = factory.create(Optional.of(VALUE));
        // then
        assertEquals(expectedValue, actualValue);
    }
    
    @Test
    public void shouldReturnNullWhenParameterIsEmpty() throws Exception {
        // given
        // when
        CreditCard actualValue = factory.create(Optional.empty());
        // then
        assertThat(actualValue, nullValue());
    }

}
