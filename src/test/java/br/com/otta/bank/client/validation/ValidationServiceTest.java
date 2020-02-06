package br.com.otta.bank.client.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.otta.bank.client.model.ClientType;
import br.com.otta.bank.client.validation.api.DocumentValidator;
import br.com.otta.bank.client.validation.exception.ValidationFailedException;
import br.com.otta.bank.client.validation.manager.DocumentValidatorManager;
import br.com.otta.bank.client.validation.model.ValidationInformation;

/**
 * Testes unitários do {@link ValidationService}.
 *
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class ValidationServiceTest {
    private static final ClientType TYPE = ClientType.PHYSICAL;
    private static final String DOCUMENT = "document";

    @Mock
    private DocumentValidatorManager validatorManager;
    @InjectMocks
    private ValidationService validationService;

    @Mock
    private DocumentValidator validator;
    @Mock
    private ValidationInformation validationInformation;

    @BeforeEach
    protected void setUp() throws Exception {
        given(validatorManager.get(TYPE)).willReturn(validator);
        given(validator.validate(DOCUMENT)).willReturn(validationInformation);
    }

    @Test
    public void shouldSuccessfullyValidateClientDocument() {
        // given
        given(validationInformation.isValid()).willReturn(Boolean.TRUE);
        // when
        boolean actualValue = validationService.validateClientDocument(TYPE, DOCUMENT);
        // then
        assertTrue(actualValue);
    }
   
    @Test
    public void shouldThrowExceptionWhenClientDocumentFailsInValidation() {
        // given
        given(validationInformation.isValid()).willReturn(Boolean.FALSE);
        // when
        Assertions.assertThrows(ValidationFailedException.class, () -> {
            validationService.validateClientDocument(TYPE, DOCUMENT);
        });
        // then
    }

}
