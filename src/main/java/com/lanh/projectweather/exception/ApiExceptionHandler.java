

package com.lanh.projectweather.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> NotFoundException(NotFoundException ex, WebRequest request) {
        String message= ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add(message);
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), "NOT_FOUND" , details);
        return handleExceptionInternal(ex, errorMessage, null, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<Object> IndexOutOfBoundsException(IndexOutOfBoundsException ex, WebRequest request) {
        String message= ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add(message);
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), "BAD_REQUEST" , details);
        return handleExceptionInternal(ex, errorMessage, null, HttpStatus.BAD_REQUEST, request);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldError = result.getFieldErrors();
        List<String> errors = new ArrayList<>();
        for(FieldError error : fieldError) {
            errors.add(error.getDefaultMessage());
        }

        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(),"Bad request", errors);
        return handleExceptionInternal(ex, errorMessage, headers, status, request);
    }
}
