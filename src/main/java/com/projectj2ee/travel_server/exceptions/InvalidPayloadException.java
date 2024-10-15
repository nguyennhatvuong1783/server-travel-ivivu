package com.projectj2ee.travel_server.exceptions;

public class InvalidPayloadException extends RuntimeException{
    public InvalidPayloadException(String message){
        super(message);
    }
}
