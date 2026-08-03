package com.spms.exception;

public class TokenExpiredException extends RuntimeException{

    public TokenExpiredException(){
        super("JWT token has expired.Please login again ");

    }
}
