package com.fsse2401.project_backend.exception.handler;

import com.fsse2401.project_backend.exception.product.DataMissingException;
import com.fsse2401.project_backend.exception.product.GetProductException;
import com.fsse2401.project_backend.exception.product.ProductNotFoundException;
import com.fsse2401.project_backend.exception.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {
    Logger logger = LoggerFactory.getLogger(ProductExceptionHandler.class);

    @ExceptionHandler(DataMissingException.class)
    public ResponseEntity<ErrorResponse> handleDataMissingException(DataMissingException e) {
        logger.warn("DataMissingException: " + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

//    @ExceptionHandler(GetProductException.class)
//    public ResponseEntity<ErrorResponse> handleGetProductException(GetProductException e) {
//        logger.info("GetProductException: " + e.getMessage());
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
//    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
        logger.warn("ProductNotFoundException: " + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
