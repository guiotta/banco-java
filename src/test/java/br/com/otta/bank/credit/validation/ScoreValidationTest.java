package br.com.otta.bank.credit.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.Lists;

import br.com.otta.bank.credit.model.ScoreData;
import br.com.otta.bank.credit.validation.api.ScoreRulesValidation;
import br.com.otta.bank.credit.validation.api.exception.ScoreValidationException;

/**
 * Testes unitários do componente {@link ScoreValidation}.
 *
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class ScoreValidationTest {
    private static final String EXCEPTION_MESSAGE = "message";

    @Mock
    private ScoreRulesValidation validator;
    private ScoreValidation scoreValidation;

    @Mock
    private ScoreData scoreData;

    @BeforeEach
    protected void setUp() {
        this.scoreValidation = new ScoreValidation(Lists.newArrayList(validator));
    }

    @Test
    public void shouldReturnTrueWhenValidationsRunSuccessfully() {
        // given
        given(validator.validate(scoreData)).willReturn(Optional.empty());
        // when
        boolean actualValue = scoreValidation.validate(scoreData);
        // then
        assertTrue(actualValue);
    }

    @Test
    public void shouldThrowExceptionWhenValidationFails() {
        // given
        given(validator.validate(scoreData)).willReturn(Optional.of(EXCEPTION_MESSAGE));
        // when
        Assertions.assertThrows(ScoreValidationException.class, () -> {
            scoreValidation.validate(scoreData);
        });
        // then
    }

}
