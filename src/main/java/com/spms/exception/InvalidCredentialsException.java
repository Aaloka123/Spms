package com.spms.exception;

//Thrown when the username or password is wrong at the login
public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){
        super("Invalid username or password");//super call the constructor of the parent
        //and passes the message to it
    }
}
