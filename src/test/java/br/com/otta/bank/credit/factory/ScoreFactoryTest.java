package br.com.otta.bank.credit.factory;

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
import br.com.otta.bank.credit.entity.Score;
import br.com.otta.bank.credit.model.ScoreData;

/**
 * Testes unitários do componente {@link ScoreFactory}.
 *
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class ScoreFactoryTest {
    private static final int MINIMAL_VALUE = 1;
    private static final int MAXIMAL_VALUE = 5;
    private static final BigDecimal OVERDRAFT_VALUE = BigDecimal.ONE;
    private static final BigDecimal CREDIT_CARD_LIMNIT_VALUE = BigDecimal.TEN;

    @Mock
    private CreditFactory creditFactory;
    @InjectMocks
    private ScoreFactory scoreFactory;

    @Mock
    private ScoreData scoreData;
    @Mock
    private Credit credit;

    @BeforeEach
    protected void setUp() throws Exception {
        given(scoreData.getOverdraft()).willReturn(OVERDRAFT_VALUE);
        given(scoreData.getCreditCardLimit()).willReturn(CREDIT_CARD_LIMNIT_VALUE);
        given(scoreData.getMinimal()).willReturn(MINIMAL_VALUE);
        given(scoreData.getMaximal()).willReturn(MAXIMAL_VALUE);
        given(creditFactory.create(Optional.of(OVERDRAFT_VALUE), Optional.of(CREDIT_CARD_LIMNIT_VALUE)))
                .willReturn(credit);
    }

    @Test
    public void shouldCorrectlyCreateScore() throws Exception {
        //given
        Score expectedValue = new Score(null, MINIMAL_VALUE, MAXIMAL_VALUE, credit);
        //when
        Score actualValue = scoreFactory.create(scoreData);
        //then
        assertEquals(expectedValue, actualValue);
    }

}
