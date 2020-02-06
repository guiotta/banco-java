package br.com.otta.bank.credit.filter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import br.com.otta.bank.credit.entity.Score;

/**
 * Classe de testes unitários do componente {@link ScoreRangeFilter}.
 *
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ScoreRangeFilterTest {
    private static final int MINIMAL = 2;
    private static final int MAXIMAL = 4;

    @InjectMocks
    private ScoreRangeFilter filter;

    @Mock
    private Score score;

    @BeforeEach
    protected void setUp() {
        given(score.getMinimal()).willReturn(MINIMAL);
        given(score.getMaximal()).willReturn(MAXIMAL);
    }

    @Test
    public void shouldReturnTrueWhenValueIsBetweenMinimalAndMaximal() {
        // given
        int value = 3;
        // when
        boolean actualValue = filter.filter(value, score);
        // then
        assertTrue(actualValue);
    }

    @Test
    public void shouldReturnTrueWhenValueIsEqualToMinimal() {
        // given
        // when
        boolean actualValue = filter.filter(MINIMAL, score);
        // then
        assertTrue(actualValue);
    }

    @Test
    public void shouldReturnTrueWhenValueIsEqualToMaximal() {
        // given
        // when
        boolean actualValue = filter.filter(MAXIMAL, score);
        // then
        assertTrue(actualValue);
    }

    @Test
    public void shouldReturnFalseWhenValueIsSmallerThanMinimal() {
        // given
        int value = 1;
        // when
        boolean actualValue = filter.filter(value, score);
        // then
        assertFalse(actualValue);
    }

    @Test
    public void shouldReturnFalseWhenValueIsBiggerThanMaximal() {
        // given
        int value = 5;
        // when
        boolean actualValue = filter.filter(value, score);
        // then
        assertFalse(actualValue);
    }

}
