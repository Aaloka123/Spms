package com.spms.exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username){
        super("username" + username + "already exists.");
    }

}
