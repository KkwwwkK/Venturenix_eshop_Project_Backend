package com.fsse2401.project_backend.exception.cartItem;

public class AddQuantityException extends RuntimeException{
    public AddQuantityException(){
        super("Add quantity failed!");
    }
}
