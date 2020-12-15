package com.spring.hotelservice.exception;

public class UsernameAlreadyExists extends Exception{

    public UsernameAlreadyExists() {
        super("Username already exists");
    }
    public UsernameAlreadyExists(String message) {
        super(message);
    }
}
