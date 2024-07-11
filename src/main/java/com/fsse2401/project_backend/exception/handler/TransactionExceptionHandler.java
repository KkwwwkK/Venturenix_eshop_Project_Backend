package com.fsse2401.project_backend.exception.handler;

import com.fsse2401.project_backend.exception.product.DataMissingException;
import com.fsse2401.project_backend.exception.response.ErrorResponse;
import com.fsse2401.project_backend.exception.transaction.PayTransactionException;
import com.fsse2401.project_backend.exception.transaction.UpdateStockException;
import com.fsse2401.project_backend.exception.transactionProduct.TransactionNotFoundException;
import com.fsse2401.project_backend.service.impl.TransactionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransactionExceptionHandler {
    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @ExceptionHandler(DataMissingException.class)
    public ResponseEntity<ErrorResponse> handleDataMissingException(DataMissingException e) {
        logger.warn("DataMissingException: " + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTransactionNotFoundException(TransactionNotFoundException e) {
        logger.warn("TransactionNotFoundException: " + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UpdateStockException.class)
    public ResponseEntity<ErrorResponse> handleUpdateStockException(UpdateStockException e) {
        logger.warn("UpdateStockException: " + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(PayTransactionException.class)
    public ResponseEntity<ErrorResponse> handlePayTransactionException(PayTransactionException e) {
        logger.warn("PayTransactionException: " + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
