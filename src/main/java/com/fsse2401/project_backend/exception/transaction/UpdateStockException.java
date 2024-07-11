package com.fsse2401.project_backend.exception.transaction;

public class UpdateStockException extends RuntimeException{
    public UpdateStockException(Integer productStock, Integer quantity){
        super(String.format("UpdateStockException: product stock - %d, quantity - %d", productStock, quantity));
    }
}
