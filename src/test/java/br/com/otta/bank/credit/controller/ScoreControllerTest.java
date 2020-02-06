package br.com.otta.bank.credit.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.google.common.collect.Lists;

import br.com.otta.bank.credit.model.ScoreData;
import br.com.otta.bank.credit.model.ScoreInformation;
import br.com.otta.bank.credit.service.ScoreService;

/**
 * Testes unitários do componente {@link ScoreController}.
 *
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class ScoreControllerTest {
    @Mock
    private ScoreService scoreService;
    @InjectMocks
    private ScoreController scoreController;

    @Mock
    private ScoreData scoreData;
    @Mock
    private ScoreInformation scoreInformation;

    @BeforeEach
    protected void setUp() {
    }

    @Test
    public void shouldCorrectlyFindAll() {
        // given
        given(scoreService.findAll()).willReturn(Lists.newArrayList(scoreInformation));
        // when
        ResponseEntity<Collection<ScoreInformation>> actualValue = scoreController.findAll();
        // then
        assertThat(actualValue.getBody(), contains(scoreInformation));
    }

    @Test
    public void shouldCorrectlyUpdate() {
        // given
        given(scoreService.update(scoreData)).willReturn(scoreInformation);
        // when
        ResponseEntity<ScoreInformation> actualValue = scoreController.update(scoreData);
        // then
        assertEquals(scoreInformation, actualValue.getBody());
    }

    @Test
    public void shouldCorrectlyInsert() {
        // given
        given(scoreService.insert(scoreData)).willReturn(scoreInformation);
        // when
        ResponseEntity<ScoreInformation> actualValue = scoreController.insert(scoreData);
        // then
        assertEquals(scoreInformation, actualValue.getBody());
    }

}
