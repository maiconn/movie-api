package com.example.movieapi.exceptions;

import com.example.movieapi.dto.ErrorDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .toList();

        return ResponseEntity.status(badRequest).body(new ErrorDTO(new Date(), badRequest.value(), errors.toString()));
    }

    @ExceptionHandler({BusinessException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorDTO> handleException(Exception ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(badRequest).body(new ErrorDTO(new Date(), badRequest.value(), ex.getMessage()));
    }

}
