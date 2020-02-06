package br.com.otta.bank.credit.factory;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.otta.bank.credit.entity.Overdraft;

/**
 * Testes unitários do componente {@link OverdraftFactory}.
 * 
 * @author Guilherme
 *
 */
public class OverdraftFactoryTest {
    private static final BigDecimal VALUE = BigDecimal.ONE;

    private OverdraftFactory overdraftFactory;

    @BeforeEach
    protected void setUp() throws Exception {
        this.overdraftFactory = new OverdraftFactory();
    }

    @Test
    public void shouldCorrectlyBuildOverdraft() throws Exception {
        // given
        Overdraft expectedValue = new Overdraft(null, VALUE);
        // when
        Overdraft actualValue = overdraftFactory.create(Optional.of(VALUE));
        // then
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void shouldReturnNullWhenParameterIsEmpty() throws Exception {
        // given
        // when
        Overdraft actualValue = overdraftFactory.create(Optional.empty());
        // then
        assertThat(actualValue, nullValue());
    }

}
