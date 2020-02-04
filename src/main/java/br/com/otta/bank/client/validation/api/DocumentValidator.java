package br.com.otta.bank.client.validation.api;

import org.apache.commons.lang3.StringUtils;

import br.com.otta.bank.client.validation.model.ValidationInformation;

public interface DocumentValidator {
    ValidationInformation validate(String document);

    /**
     * Método para validar se todos os charateres são numéricos.
     */
    default boolean validateOnlyDigits(String document) {
        return StringUtils.isNumeric(document);
    }
}
