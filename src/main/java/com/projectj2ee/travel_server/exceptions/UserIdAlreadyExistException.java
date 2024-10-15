package com.projectj2ee.travel_server.exceptions;

public class UserIdAlreadyExistException extends RuntimeException{
    public  UserIdAlreadyExistException(String message){
        super(message);
    }
}
