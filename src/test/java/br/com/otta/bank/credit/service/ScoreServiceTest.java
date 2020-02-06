package br.com.otta.bank.credit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.otta.bank.credit.entity.Score;
import br.com.otta.bank.credit.factory.ScoreFactory;
import br.com.otta.bank.credit.mapper.ScoreInformationMapper;
import br.com.otta.bank.credit.model.ScoreData;
import br.com.otta.bank.credit.model.ScoreInformation;
import br.com.otta.bank.credit.repository.ScoreRepository;
import br.com.otta.bank.credit.validation.ScoreValidation;
import br.com.otta.bank.credit.validation.api.exception.ScoreValidationException;

/**
 * Testes unitários do componente {@link ScoreService}.
 *
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class ScoreServiceTest {
    private static final Long ID = Long.valueOf(1l);

    @Mock
    private ScoreInformationMapper mapper;

    @Mock
    private ScoreRepository repository;

    @Mock
    private ScoreFactory scoreFactory;

    @Mock
    private ScoreValidation validation;
    @InjectMocks
    private ScoreService scoreService;

    @Mock
    private ScoreData scoreData;
    @Mock
    private Score score;
    @Mock
    private Score savedScore;
    @Mock
    private ScoreInformation scoreInformation;

    @BeforeEach
    protected void setUp() {
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingAndDontFindScoreInDatabase() {
        // given
        given(repository.findById(ID)).willReturn(Optional.empty());
        given(scoreData.getId()).willReturn(ID);
        // when
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            scoreService.update(scoreData);
        });
        // then
    }

    @Test
    public void shouldReturnScoreInformationOfUpdatedScore() {
        // given
        given(scoreData.getId()).willReturn(ID);
        given(repository.findById(ID)).willReturn(Optional.of(score));
        given(validation.validate(scoreData)).willReturn(Boolean.TRUE);
        given(scoreFactory.create(scoreData)).willReturn(score);
        given(repository.save(score)).willReturn(savedScore);
        given(mapper.map(savedScore)).willReturn(scoreInformation);
        // when
        ScoreInformation actualValue = scoreService.update(scoreData);
        // then
        assertEquals(scoreInformation, actualValue);
    }

    @Test
    public void shouldDeleteOldScoreWhenUpdating() {
        // given
        given(scoreData.getId()).willReturn(ID);
        given(repository.findById(ID)).willReturn(Optional.of(score));
        given(validation.validate(scoreData)).willReturn(Boolean.TRUE);
        given(scoreFactory.create(scoreData)).willReturn(score);
        given(repository.save(score)).willReturn(savedScore);
        given(mapper.map(savedScore)).willReturn(scoreInformation);
        // when
        scoreService.update(scoreData);
        // then
        verify(repository).deleteById(ID);
    }

    @Test
    public void shouldThrowExceptionWhenValidationFailsInUpdate() {
        // given
        given(scoreData.getId()).willReturn(ID);
        given(repository.findById(ID)).willReturn(Optional.of(score));
        given(validation.validate(scoreData)).willThrow(ScoreValidationException.class);
        // when
        Assertions.assertThrows(ScoreValidationException.class, () -> {
            scoreService.update(scoreData);
        });
        // then
    }

    @Test
    public void shouldReturnScoreInformationOfInsertedScore() throws Exception {
        // given
        given(validation.validate(scoreData)).willReturn(Boolean.TRUE);
        given(scoreFactory.create(scoreData)).willReturn(score);
        given(repository.save(score)).willReturn(savedScore);
        given(mapper.map(savedScore)).willReturn(scoreInformation);
        // when
        ScoreInformation actualValue = scoreService.insert(scoreData);
        // then
        assertEquals(scoreInformation, actualValue);
    }

    @Test
    public void shouldThrowExceptionWhenValidationFailsInInsert() throws Exception {
        // given
        given(validation.validate(scoreData)).willThrow(ScoreValidationException.class);
        // when
        Assertions.assertThrows(ScoreValidationException.class, () -> {
            scoreService.insert(scoreData);
        });
        // then
    }

}
