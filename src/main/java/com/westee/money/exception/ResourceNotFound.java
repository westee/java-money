package com.westee.money.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFound extends ServiceException{
    public ResourceNotFound(String message) {
        super(message);
        this.setStatusCode(HttpStatus.NOT_FOUND.value());
    }
}
