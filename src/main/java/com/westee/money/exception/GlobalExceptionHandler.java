package com.westee.money.exception;

import lombok.val;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    ResponseEntity<?> handleResourceNotFoundException(ServiceException exception) {
        val errResponse = ErrorResponse.builder()
                .statusCode(exception.getStatusCode())
                .message(exception.getMessage())
                .code(exception.getErrorCode())
                .errorType(exception.getErrorType())
                .build();
        return ResponseEntity.status(exception.getStatusCode() != 0 ? exception.getStatusCode()
                : HttpStatus.INTERNAL_SERVER_ERROR.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(errResponse);
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    ResponseEntity<?> handleIncorrectCredentialsException(IncorrectCredentialsException exception) {
        val errResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .code(BizErrorCode.INCORRECT_CREDENTIALS)
                .errorType(ServiceException.ErrorType.Client)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(errResponse);
    }
}
