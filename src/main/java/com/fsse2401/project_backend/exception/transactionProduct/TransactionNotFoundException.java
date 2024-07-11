package com.fsse2401.project_backend.exception.transactionProduct;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(){
        super("Transaction Not Found!");
    }
}
