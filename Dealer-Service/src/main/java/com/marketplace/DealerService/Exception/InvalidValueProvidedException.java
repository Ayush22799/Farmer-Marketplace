package com.marketplace.DealerService.Exception;

public class InvalidValueProvidedException extends RuntimeException{
    String message;
    public InvalidValueProvidedException(String message){
        super(message);
        this.message = message;
    }
}
