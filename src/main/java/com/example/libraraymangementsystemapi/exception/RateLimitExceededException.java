package com.example.libraraymangementsystemapi.exception;

public class RateLimitExceededException extends RuntimeException{
    public RateLimitExceededException(String message){
        super(message);
    }
}
