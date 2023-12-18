package com.evoke.cartshop.exceptions;

public class DuplicateAccountException extends RuntimeException{

    public DuplicateAccountException(String message){
        super(message);
    }
}
