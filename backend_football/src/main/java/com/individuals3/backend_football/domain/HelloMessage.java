package com.individuals3.backend_football.domain;

public class HelloMessage {

    private String message;

    public HelloMessage() {
    }

    public HelloMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return message;
    }

    public void setName(String name) {
        this.message = name;
    }
}
