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
 * Testes unitários do componente {@link PhysicalDocumentValidator}.
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class PhysicalDocumentValidatorTest {
    private static final String VALID_CPF = "13541904089";
    private static final String INVALID_CPF = "13541904111";
    private static final String SHORT_CPF = "1111";
    private static final String CPF_WITH_INVALID_CHAR = "13541904aaa";

    @Mock
    private SafeguardCheck safeguardCheck;
    @InjectMocks
    private PhysicalDocumentValidator physicalDocumentValidator;

    @BeforeEach
    protected void setUp() throws Exception {
    }

    @Test
    public void shouldNotFindErrorsForValidCPF() throws Exception {
        // given
        given(safeguardCheck.elementOf(VALID_CPF, ParametroTipo.CPF)).willReturn(safeguardCheck);
        given(safeguardCheck.validate()).willReturn(safeguardCheck);
        given(safeguardCheck.hasError()).willReturn(Boolean.FALSE);
        // when
        ValidationInformation actualValue = physicalDocumentValidator.validate(VALID_CPF);
        // then
        assertTrue(actualValue.isValid());
        assertThat(actualValue.getMessages(), empty());
    }

    @Test
    public void shouldFindErrorWhenCPFSizeIsNotCorrect() throws Exception {
        // given
        given(safeguardCheck.elementOf(SHORT_CPF, ParametroTipo.CPF)).willReturn(safeguardCheck);
        given(safeguardCheck.validate()).willReturn(safeguardCheck);
        given(safeguardCheck.hasError()).willReturn(Boolean.FALSE);
        // when
        ValidationInformation actualValue = physicalDocumentValidator.validate(SHORT_CPF);
        // then
        assertFalse(actualValue.isValid());
        assertThat(actualValue.getMessages(), contains("Invalid size for CPF. Expected 11 characters."));
    }

    @Test
    public void shouldFindErrorWhenPFHasInvalidCharactert() throws Exception {
        // given
        given(safeguardCheck.elementOf(CPF_WITH_INVALID_CHAR, ParametroTipo.CPF)).willReturn(safeguardCheck);
        given(safeguardCheck.validate()).willReturn(safeguardCheck);
        given(safeguardCheck.hasError()).willReturn(Boolean.FALSE);
        // when
        ValidationInformation actualValue = physicalDocumentValidator.validate(CPF_WITH_INVALID_CHAR);
        // then
        assertFalse(actualValue.isValid());
        assertThat(actualValue.getMessages(), contains("Invalid content for CPF. Expected only digits."));
    }

    @Test
    public void shouldFindErrorWhenCNPJIsInvalid() throws Exception {
        // given
        given(safeguardCheck.elementOf(INVALID_CPF, ParametroTipo.CPF)).willReturn(safeguardCheck);
        given(safeguardCheck.validate()).willReturn(safeguardCheck);
        given(safeguardCheck.hasError()).willReturn(Boolean.TRUE);
        // when
        ValidationInformation actualValue = physicalDocumentValidator.validate(INVALID_CPF);
        // then
        assertFalse(actualValue.isValid());
        assertThat(actualValue.getMessages(), contains("Document is not a valid CPF."));
    }

}
