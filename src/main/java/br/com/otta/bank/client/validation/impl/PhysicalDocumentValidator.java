package br.com.otta.bank.client.validation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.otta.bank.client.validation.api.DocumentValidator;
import br.com.otta.bank.client.validation.model.ValidationInformation;
import br.com.safeguard.check.SafeguardCheck;
import br.com.safeguard.types.ParametroTipo;

/**
 * Componente para realizar a validação de um documento de pessoa física.</br>
 * Retorna um {@link ValidationInformation} contendo os erros de validação encontrados.
 *
 * @author Guilherme
 *
 */
@Component
public class PhysicalDocumentValidator implements DocumentValidator {
    private static final int EXPECTED_DOCUMENT_SIZE = 11;

    private final SafeguardCheck safeguardCheck;

    @Autowired
    public PhysicalDocumentValidator(SafeguardCheck safeguardCheck) {
        this.safeguardCheck = safeguardCheck;
    }

    @Override
    public ValidationInformation validate(String document) {
        ValidationInformation validationInformation = new ValidationInformation();

        if (document.length() != EXPECTED_DOCUMENT_SIZE) {
            validationInformation.addMessage("Invalid size for CPF. Expected 11 characters.");
        }
        if (!this.validateOnlyDigits(document)) {
            validationInformation.addMessage("Invalid content for CPF. Expected only digits.");
        }
        if (safeguardCheck.elementOf(document, ParametroTipo.CPF).validate().hasError()) {
            validationInformation.addMessage("Document is not a valid CPF.");
        }

        return validationInformation;
    }
}
