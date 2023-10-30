package dev.joelfrancisco.abp.controllers;

import dev.joelfrancisco.abp.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        List<String> collect = ex.getBindingResult()
                .getAllErrors().stream()
                .map(p -> ((FieldError) p).getField() + " " + p.getDefaultMessage())
                .toList();
        errors.put("error", collect.toString());
        return errors;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ValidationException.class)
    public Map<String, String> handleValidationExceptions422(ValidationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return errors;
    }
}
