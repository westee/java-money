package com.westee.money.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidParameterException extends ServiceException{
    public InvalidParameterException(String message) {
        super(message);
        this.setErrorCode("INVALID_PARAMETER");
        this.setErrorType(ErrorType.Client);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
    }
}
