package br.com.otta.bank.client.validation.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.otta.bank.client.model.ClientType;
import br.com.otta.bank.client.validation.api.DocumentValidator;
import br.com.otta.bank.client.validation.impl.LegalDocumentValidator;
import br.com.otta.bank.client.validation.impl.PhysicalDocumentValidator;

/**
 * Componente para realizar o controle de qual implementação de {@link DocumentValidator} deve ser utilizada.
 *
 * @author Guilherme
 *
 */
@Component
public class DocumentValidatorManager {
    private final PhysicalDocumentValidator physicalDocumentValidator;
    private final LegalDocumentValidator legalDocumentValidatior;

    @Autowired
    public DocumentValidatorManager(PhysicalDocumentValidator physicalDocumentValidator,
            LegalDocumentValidator legalDocumentValidatior) {
        this.physicalDocumentValidator = physicalDocumentValidator;
        this.legalDocumentValidatior = legalDocumentValidatior;
    }

    public DocumentValidator get(ClientType type) {
        switch (type) {
        case PHYSICAL:
            return physicalDocumentValidator;
        case LEGAL:
            return legalDocumentValidatior;
        default:
            throw new IllegalArgumentException(String.format("Could not find validator for type %s.", type));
        }
    }
}
