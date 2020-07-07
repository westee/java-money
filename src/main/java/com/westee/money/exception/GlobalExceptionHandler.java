package com.westee.money.exception;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        val errResponse = ErrorResponse.builder()
                .statusCode(exception.getStatusCode())
                .message(exception.getMessage())
                .code(exception.getErrorCode())
                .errorType(exception.getErrorType())
                .build();
        return ResponseEntity.status(exception.getStatusCode() != 0 ? exception.getStatusCode()
                : HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(errResponse);
    }
}
