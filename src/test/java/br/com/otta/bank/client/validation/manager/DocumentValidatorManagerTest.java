package br.com.otta.bank.client.validation.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.otta.bank.client.model.ClientType;
import br.com.otta.bank.client.validation.api.DocumentValidator;
import br.com.otta.bank.client.validation.impl.LegalDocumentValidator;
import br.com.otta.bank.client.validation.impl.PhysicalDocumentValidator;

/**
 * Testes unitários do componente {@link DocumentValidatorManager}.
 *
 * @author Guilherme
 *
 */
@ExtendWith(MockitoExtension.class)
public class DocumentValidatorManagerTest {
    @Mock
    private LegalDocumentValidator legalDocumentValidatior;

    @Mock
    private PhysicalDocumentValidator physicalDocumentValidator;
    @InjectMocks
    private DocumentValidatorManager documentValidatorManager;

    @BeforeEach
    protected void setUp() throws Exception {
    }

    @Test
    public void shouldGetPhysicalValidator() throws Exception {
        // given
        // when
        DocumentValidator actualValue = documentValidatorManager.get(ClientType.PHYSICAL);
        // then
        assertEquals(physicalDocumentValidator, actualValue);
    }

    @Test
    public void shouldGetLegalValidator() throws Exception {
        // given
        // when
        DocumentValidator actualValue = documentValidatorManager.get(ClientType.LEGAL);
        // then
        assertEquals(legalDocumentValidatior, actualValue);
    }

}
