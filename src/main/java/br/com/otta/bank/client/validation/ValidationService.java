package br.com.otta.bank.client.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otta.bank.client.model.ClientType;
import br.com.otta.bank.client.validation.api.DocumentValidator;
import br.com.otta.bank.client.validation.exception.ValidationFailedException;
import br.com.otta.bank.client.validation.manager.DocumentValidatorManager;
import br.com.otta.bank.client.validation.model.ValidationInformation;

/**
 * Serviço para realizar as validações dos clientes.
 *
 * @author Guilherme
 *
 */
@Service
public class ValidationService {
    private final DocumentValidatorManager validatorManager;

    @Autowired
    public ValidationService(DocumentValidatorManager validatorManager) {
        this.validatorManager = validatorManager;
    }

    /**
     * Valida o documento para criação de um cliente.</br>
     * Caso não exista nenhum problema, irá retornar um true. Caso contrário, dispara uma {@link ValidationFailedException}.
     */
    public boolean validateClientDocument(ClientType type, String document) throws ValidationFailedException {
        DocumentValidator validator = validatorManager.get(type);
        ValidationInformation validateInformation = validator.validate(document);
        if (!validateInformation.isValid()) {
            throw new ValidationFailedException(validateInformation.getMessages());
        }

        return true;
    }
}
