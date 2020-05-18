package com.api.starWars.exception;

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

@RestControllerAdvice
public class CustomExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NotFoundError.class)
    public ResponseEntity<StandardError> notFoundError() {
        int status = HttpStatus.NOT_FOUND.value();
        StandardError error = new StandardError(status, Messages.PLANET_NOT_FOUND, "");
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<StandardError>> validationException(MethodArgumentNotValidException ex) {
        int status = HttpStatus.BAD_REQUEST.value();
        List<StandardError> errors = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors
                .stream()
                .forEach(fieldError -> {
                    String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                    StandardError error = new StandardError(status, mensagem, fieldError.getField());
                    errors.add(error);
                });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PlanetExistError.class)
    public ResponseEntity<StandardError> planetExistError() {
        int status = HttpStatus.BAD_REQUEST.value();
        StandardError error = new StandardError(status, Messages.EXISTS_NAME, "nome");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IntegrationError.class)
    public ResponseEntity<StandardError> integrationException() {
        int status = HttpStatus.SERVICE_UNAVAILABLE.value();
        StandardError error = new StandardError(status, Messages.SERVICE_UNAVAILABLE);
        return ResponseEntity.status(status).body(error);
    }

}
