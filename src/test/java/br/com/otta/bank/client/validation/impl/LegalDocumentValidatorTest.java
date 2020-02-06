package br.com.otta.bank.client.validation.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.otta.bank.client.validation.model.ValidationInformation;
import br.com.safeguard.check.SafeguardCheck;
import br.com.safeguard.types.ParametroTipo;

/**
 * Testes unitários do componente {@link LegalDocumentValidator}.
 * 
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class LegalDocumentValidatorTest {
    private static final String VALID_CNPJ = "18519692000144";
    private static final String INVALID_CNPJ = "18519692000111";
    private static final String SHORT_CNPJ = "1111";
    private static final String CNPJ_WITH_INVALID_CHAR = "185196920001aa";

    @Mock
    private SafeguardCheck safeguardCheck;
    @InjectMocks
    private LegalDocumentValidator legalDocumentValidator;

    @BeforeEach
    protected void setUp() throws Exception {
    }

    @Test
    public void shouldNotFindErrorsForValidCNPJ() throws Exception {
        // given
        given(safeguardCheck.elementOf(VALID_CNPJ, ParametroTipo.CNPJ)).willReturn(safeguardCheck);
        given(safeguardCheck.validate()).willReturn(safeguardCheck);
        given(safeguardCheck.hasError()).willReturn(Boolean.FALSE);
        // when
        ValidationInformation actualValue = legalDocumentValidator.validate(VALID_CNPJ);
        // then
        assertTrue(actualValue.isValid());
        assertThat(actualValue.getMessages(), empty());
    }

    @Test
    public void shouldFindErrorWhenCNPJSizeIsNotCorrect() throws Exception {
        // given
        given(safeguardCheck.elementOf(SHORT_CNPJ, ParametroTipo.CNPJ)).willReturn(safeguardCheck);
        given(safeguardCheck.validate()).willReturn(safeguardCheck);
        given(safeguardCheck.hasError()).willReturn(Boolean.FALSE);
        // when
        ValidationInformation actualValue = legalDocumentValidator.validate(SHORT_CNPJ);
        // then
        assertFalse(actualValue.isValid());
        assertThat(actualValue.getMessages(), contains("Invalid size for CNPJ. Expected 14 characters."));
    }

    @Test
    public void shouldFindErrorWhenCNPJHasInvalidCharactert() throws Exception {
        // given
        given(safeguardCheck.elementOf(CNPJ_WITH_INVALID_CHAR, ParametroTipo.CNPJ)).willReturn(safeguardCheck);
        given(safeguardCheck.validate()).willReturn(safeguardCheck);
        given(safeguardCheck.hasError()).willReturn(Boolean.FALSE);
        // when
        ValidationInformation actualValue = legalDocumentValidator.validate(CNPJ_WITH_INVALID_CHAR);
        // then
        assertFalse(actualValue.isValid());
        assertThat(actualValue.getMessages(), contains("Invalid content for CNPJ. Expected only digits."));
    }

    @Test
    public void shouldFindErrorWhenCNPJIsInvalid() throws Exception {
        // given
        given(safeguardCheck.elementOf(INVALID_CNPJ, ParametroTipo.CNPJ)).willReturn(safeguardCheck);
        given(safeguardCheck.validate()).willReturn(safeguardCheck);
        given(safeguardCheck.hasError()).willReturn(Boolean.TRUE);
        // when
        ValidationInformation actualValue = legalDocumentValidator.validate(INVALID_CNPJ);
        // then
        assertFalse(actualValue.isValid());
        assertThat(actualValue.getMessages(), contains("Document is not a valid CNPJ."));
    }

}
