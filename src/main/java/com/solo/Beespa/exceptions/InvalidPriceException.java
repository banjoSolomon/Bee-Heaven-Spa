package com.solo.Beespa.exceptions;

public class InvalidPriceException extends RuntimeException{
    public InvalidPriceException(String message) {
        super(message);
    }
}
