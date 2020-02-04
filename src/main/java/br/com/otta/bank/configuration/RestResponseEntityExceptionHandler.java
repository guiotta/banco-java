package br.com.otta.bank.configuration;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.otta.bank.client.validation.exception.ValidationFailedException;

/**
 * Classe para controlar o tratamento de erros na saída do Serviço.
 * 
 * @author Guilherme
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ValidationFailedException.class })
    protected ResponseEntity<Collection<String>> handleValidationException(ValidationFailedException ex,
            WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
    }
}
