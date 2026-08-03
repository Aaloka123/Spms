package com.spms.exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username){
        super("Username '" + username + "' already exists.");
    }

}
