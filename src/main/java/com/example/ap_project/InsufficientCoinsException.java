package com.example.ap_project;

public class InsufficientCoinsException extends Exception{
    public InsufficientCoinsException(String message){
        super(message);
    }
}
