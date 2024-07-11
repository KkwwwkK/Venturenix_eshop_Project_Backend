package com.fsse2401.project_backend.exception.transaction;

public class PayTransactionException extends RuntimeException{
    public PayTransactionException(){
        super("Status Error!");
    }
}
