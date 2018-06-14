package com.microvish.dropwizard.api.model.exception;

public class InvalidRequestException extends Exception {
    public InvalidRequestException(final String message) {
        super(message);
    }
}
