package br.com.otta.bank.credit.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.otta.bank.credit.entity.Score;
import br.com.otta.bank.credit.extractor.LimitValuesExtractor;
import br.com.otta.bank.credit.model.LimitValues;
import br.com.otta.bank.credit.model.ScoreInformation;

/**
 * Testes unitáriso do componente {@link ScoreInformationMapper}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class ScoreInformationMapperTest {
    private static final Long ID = Long.valueOf(100l);
    private static final int MINIMAL = 1;
    private static final int MAXIMAL = 2;
    private static final String OVERDRAFT = "overdraft";
    private static final String CREDIT_CARD = "creditCard";

    @Mock
    private LimitValuesExtractor extractor;
    @InjectMocks
    private ScoreInformationMapper scoreInformationMapper;

    @Mock
    private Score score;
    @Mock
    private LimitValues limitValues;

    @BeforeEach
    protected void setUp() {
        given(extractor.extract(score)).willReturn(limitValues);
        given(score.getId()).willReturn(ID);
        given(score.getMinimal()).willReturn(MINIMAL);
        given(score.getMaximal()).willReturn(MAXIMAL);
        given(limitValues.getOverdraft()).willReturn(OVERDRAFT);
        given(limitValues.getCreditCardLimit()).willReturn(CREDIT_CARD);
    }

    @Test
    public void shouldCorrectlyMapScoreInformation() {
        // given
        ScoreInformation expectedValue = new ScoreInformation(ID, MINIMAL, MAXIMAL, OVERDRAFT, CREDIT_CARD);
        // when
        ScoreInformation actualValue = scoreInformationMapper.map(score);
        // then
        assertEquals(expectedValue, actualValue);

    }

}
