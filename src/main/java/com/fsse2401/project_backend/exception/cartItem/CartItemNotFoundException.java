package com.fsse2401.project_backend.exception.cartItem;

public class CartItemNotFoundException extends RuntimeException{
    public CartItemNotFoundException(){
        super("No Cart Item Found!");
    }
}
