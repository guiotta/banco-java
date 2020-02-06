package br.com.otta.bank.credit.extractor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.otta.bank.credit.entity.Credit;
import br.com.otta.bank.credit.entity.CreditCard;
import br.com.otta.bank.credit.entity.Overdraft;
import br.com.otta.bank.credit.entity.Score;
import br.com.otta.bank.credit.model.LimitValues;

/**
 * Testes unitários do componente {@link LimitValuesExtractor}.
 *
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class LimitValuesExtractorTest {
    private static final String EMPTY_VALUE_LABEL = "Blocked";
    private static final BigDecimal NUMERIC_VALUE = BigDecimal.TEN;
    private static final String EXPECTED_FORMATTED_VALUE = "R$ 10,00";

    @InjectMocks
    private LimitValuesExtractor limitValuesExtractor;

    @Mock
    private Score score;
    @Mock
    private Credit credit;
    @Mock
    private Overdraft overdraft;
    @Mock
    private CreditCard creditCard;

    @BeforeEach
    protected void setUp() {
    }

    @Test
    public void shouldCreateLimitValuesWithEmptyValueLabelWhenCreditIsNull() {
        // given
        LimitValues expectedValue = new LimitValues(EMPTY_VALUE_LABEL, EMPTY_VALUE_LABEL);
        given(score.getCredit()).willReturn(null);
        // when
        LimitValues actualValue = limitValuesExtractor.extract(score);
        // then
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void shouldCorrectlyCreateLimitValues() {
        // given
        LimitValues expectedValue = new LimitValues(EXPECTED_FORMATTED_VALUE, EXPECTED_FORMATTED_VALUE);
        given(score.getCredit()).willReturn(credit);
        given(credit.getOverdraft()).willReturn(overdraft);
        given(credit.getCreditCard()).willReturn(creditCard);
        given(overdraft.getValue()).willReturn(NUMERIC_VALUE);
        given(creditCard.getValue()).willReturn(NUMERIC_VALUE);

        // when
        LimitValues actualValue = limitValuesExtractor.extract(score);
        // then
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void shouldCorrectlyCreateLimitValuesWhenOverdraftIsNull() {
        // given
        LimitValues expectedValue = new LimitValues(EMPTY_VALUE_LABEL, EXPECTED_FORMATTED_VALUE);
        given(score.getCredit()).willReturn(credit);
        given(credit.getOverdraft()).willReturn(null);
        given(credit.getCreditCard()).willReturn(creditCard);
        given(creditCard.getValue()).willReturn(NUMERIC_VALUE);

        // when
        LimitValues actualValue = limitValuesExtractor.extract(score);
        // then
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void shouldCorrectlyCreateLimitValuesWhenCreditCardLimitIsNull() {
        // given
        LimitValues expectedValue = new LimitValues(EXPECTED_FORMATTED_VALUE, EMPTY_VALUE_LABEL);
        given(score.getCredit()).willReturn(credit);
        given(credit.getOverdraft()).willReturn(overdraft);
        given(credit.getCreditCard()).willReturn(null);
        given(overdraft.getValue()).willReturn(NUMERIC_VALUE);

        // when
        LimitValues actualValue = limitValuesExtractor.extract(score);
        // then
        assertEquals(expectedValue, actualValue);
    }

}
