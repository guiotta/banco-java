package br.com.otta.bank.credit.factory;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.otta.bank.credit.entity.Credit;
import br.com.otta.bank.credit.entity.CreditCard;
import br.com.otta.bank.credit.entity.Overdraft;

/**
 * Testes unitários do componente {@link creditFactory}.
 *
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class CreditFactoryTest {
    private static final BigDecimal VALUE = BigDecimal.ONE;

    @Mock
    private CreditCardFactory creditCardFactory;

    @Mock
    private OverdraftFactory overdraftFactory;
    @InjectMocks
    private CreditFactory creditFactory;

    @Mock
    private Overdraft overdraft;
    @Mock
    private CreditCard creditCard;

    @BeforeEach
    protected void setUp() throws Exception {
    }

    @Test
    public void shouldCorrectlyBuildCreditCard() throws Exception {
        // given
        Credit expectedValue = new Credit(null, overdraft, creditCard);

        given(overdraftFactory.create(Optional.of(VALUE))).willReturn(overdraft);
        given(creditCardFactory.create(Optional.of(VALUE))).willReturn(creditCard);
        // when
        Credit actualValue = creditFactory.create(Optional.of(VALUE), Optional.of(VALUE));
        // then
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void shouldReturnNullWhenParametersAreEmpty() {
        // given
        // when
        Credit actualValue = creditFactory.create(Optional.empty(), Optional.empty());
        // then
        assertThat(actualValue, nullValue());
    }

    @Test
    public void shouldCorrectlyBuildCreditCardWhenOneParameterIsEmpty() throws Exception {
        // given
        Credit expectedValue = new Credit(null, overdraft, null);

        given(overdraftFactory.create(Optional.of(VALUE))).willReturn(overdraft);
        given(creditCardFactory.create(Optional.empty())).willReturn(null);
        // when
        Credit actualValue = creditFactory.create(Optional.of(VALUE), Optional.empty());
        // then
        assertEquals(expectedValue, actualValue);
    }

}
