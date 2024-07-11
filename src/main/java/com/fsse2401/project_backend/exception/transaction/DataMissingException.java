package com.fsse2401.project_backend.exception.transaction;

public class DataMissingException extends RuntimeException{
    public DataMissingException(String missingdata){
        super("Data not found: " + missingdata);
    }
}
