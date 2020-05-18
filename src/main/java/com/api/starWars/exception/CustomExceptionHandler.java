package com.api.starWars.exception;

import com.api.starWars.dto.EnvelopeErrorDTO;
import com.api.starWars.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para interceptar exceções
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NotFoundError.class)
    public ResponseEntity<EnvelopeErrorDTO> notFoundError() {
        int status = HttpStatus.NOT_FOUND.value();
        EnvelopeErrorDTO error = new EnvelopeErrorDTO(status, Messages.PLANET_NOT_FOUND, "");
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<EnvelopeErrorDTO>> validationException(MethodArgumentNotValidException ex) {
        int status = HttpStatus.BAD_REQUEST.value();
        List<EnvelopeErrorDTO> errors = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors
                .stream()
                .forEach(fieldError -> {
                    String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                    EnvelopeErrorDTO error = new EnvelopeErrorDTO(status, mensagem, fieldError.getField());
                    errors.add(error);
                });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PlanetExistError.class)
    public ResponseEntity<EnvelopeErrorDTO> planetExistError() {
        int status = HttpStatus.BAD_REQUEST.value();
        EnvelopeErrorDTO error = new EnvelopeErrorDTO(status, Messages.EXISTS_NAME, "nome");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IntegrationError.class)
    public ResponseEntity<EnvelopeErrorDTO> integrationException() {
        int status = HttpStatus.SERVICE_UNAVAILABLE.value();
        EnvelopeErrorDTO error = new EnvelopeErrorDTO(status, Messages.SERVICE_UNAVAILABLE);
        return ResponseEntity.status(status).body(error);
    }

}
