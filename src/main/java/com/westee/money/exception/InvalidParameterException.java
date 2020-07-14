package com.westee.money.exception;

import org.springframework.http.HttpStatus;

public class InvalidParameterException extends ServiceException{
    public InvalidParameterException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
        this.setErrorCode(BizErrorCode.INVALID_PARAMETER);
        this.setErrorType(ErrorType.Client);
    }
}
