package br.com.otta.bank.client.validation.impl;

import org.springframework.stereotype.Component;

import br.com.otta.bank.client.validation.api.DocumentValidator;
import br.com.otta.bank.client.validation.model.ValidationInformation;

/**
 * Componente para realizar a validação de um documento de pessoa juridica.</br>
 * Retorna um {@link ValidationInformation} contendo os erros de validação encontrados.
 *
 * @author Guilherme
 *
 */
@Component
public class LegalDocumentValidator implements DocumentValidator {
    private static final int EXPECTED_DOCUMENT_SIZE = 14;

    @Override
    public ValidationInformation validate(String document) {
        ValidationInformation validationInformation = new ValidationInformation();

        if (document.length() != EXPECTED_DOCUMENT_SIZE) {
            validationInformation.addMessage("Invalid size for CNPJ. Expected 14 characters.");
        }
        if (!this.validateOnlyDigits(document)) {
            validationInformation.addMessage("Invalid content for CNPJ. Expected only digits.");
        }

        return validationInformation;
    }

}
