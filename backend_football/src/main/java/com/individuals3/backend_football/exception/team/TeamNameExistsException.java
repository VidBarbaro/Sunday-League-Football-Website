package com.individuals3.backend_football.exception.team;

public class TeamNameExistsException extends Exception {

    public TeamNameExistsException(String message) {
        super(message);
    }
}