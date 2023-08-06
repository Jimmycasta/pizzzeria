package com.jimmycasta.pizzeria.Exception;

public class EmailApiException extends RuntimeException{
    public EmailApiException(){
        super("Error sending email...");
    }
}
