package com.fsse2401.project_backend.exception.product;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(){
        super("Product Not Found!");
    }
}
