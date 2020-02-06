package br.com.otta.bank.credit.validation.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.Lists;

import br.com.otta.bank.credit.entity.Score;
import br.com.otta.bank.credit.model.ScoreData;
import br.com.otta.bank.credit.repository.ScoreRepository;

/**
 * Testes unitários do componente {@link ScoreRangeValidation}.
 *
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class ScoreRangeValidationTest {
    private static final Long ID_1 = Long.valueOf(1l);
    private static final Long ID_2 = Long.valueOf(2l);
    private static final int MINIMAL = 1;
    private static final int MAXIMAL = 5;

    @Mock
    private ScoreRepository repository;
    @InjectMocks
    private ScoreRangeValidation scoreRangeValidation;

    @Mock
    private ScoreData scoreData;
    @Mock
    private Score score;

    @BeforeEach
    protected void setUp() {
        given(scoreData.getMinimal()).willReturn(MINIMAL);
        given(scoreData.getMaximal()).willReturn(MAXIMAL);
    }

    @Test
    public void shouldReturnEmptyWhenRepositoryReturnEmptyCollectionForMinimalAndMaximal() {
        // given
        given(repository.findByScoreRange(MINIMAL)).willReturn(Lists.newArrayList());
        given(repository.findByScoreRange(MAXIMAL)).willReturn(Lists.newArrayList());
        // when
        Optional<String> actualValue = scoreRangeValidation.validate(scoreData);
        // then
        assertFalse(actualValue.isPresent());
    }

    @Test
    public void shouldReturnEmptyWhenIdIsEqualInScoreDataAndScoreFindByRepository() {
        // given
        given(repository.findByScoreRange(MINIMAL)).willReturn(Lists.newArrayList(score));
        given(repository.findByScoreRange(MAXIMAL)).willReturn(Lists.newArrayList(score));
        given(score.getId()).willReturn(ID_1);
        given(scoreData.getId()).willReturn(ID_1);
        // when
        Optional<String> actualValue = scoreRangeValidation.validate(scoreData);
        // then
        assertFalse(actualValue.isPresent());
    }

    @Test
    public void shouldReturnOptionalStringWhenIdIsDifferentInScoreDataAndScoreFindByRepository() {
        // given
        String expectedMessage = "Range of Score already in use: 1 - 5.";
        given(repository.findByScoreRange(MINIMAL)).willReturn(Lists.newArrayList(score));
        given(repository.findByScoreRange(MAXIMAL)).willReturn(Lists.newArrayList(score));
        given(score.getId()).willReturn(ID_1);
        given(scoreData.getId()).willReturn(ID_2);
        // when
        Optional<String> actualValue = scoreRangeValidation.validate(scoreData);
        // then
        assertTrue(actualValue.isPresent());
        assertEquals(expectedMessage, actualValue.get());
    }

}
