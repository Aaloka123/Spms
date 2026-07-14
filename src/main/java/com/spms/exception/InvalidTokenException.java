package com.spms.exception;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(){
        super("JWT token is invalid");
    }
    public InvalidTokenException(String message){
        super(message);
    }
}
