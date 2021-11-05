package com.individuals3.backend_football.exception.domain;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }
}
