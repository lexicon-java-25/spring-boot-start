package com.example.demo.model;

public class Greeting {
    private String message;

    public Greeting(String name) {
        this.message = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
