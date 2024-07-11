package com.fsse2401.project_backend.exception.handler;

import com.fsse2401.project_backend.exception.cartItem.AddQuantityException;
import com.fsse2401.project_backend.exception.cartItem.CartItemNotFoundException;
import com.fsse2401.project_backend.exception.cartItem.GetUserCartException;
import com.fsse2401.project_backend.exception.product.DataMissingException;
import com.fsse2401.project_backend.exception.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CartItemExceptionHandler {
    Logger logger = LoggerFactory.getLogger(CartItemExceptionHandler.class);


    @ExceptionHandler(DataMissingException.class)
    public ResponseEntity<ErrorResponse> handleDataMissingException(DataMissingException e) {
        logger.warn("DataMissingException: " + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(AddQuantityException.class)
    public ResponseEntity<ErrorResponse> handleAddQuantityException(AddQuantityException e) {
        logger.warn("AddQuantityException: " + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(GetUserCartException.class)
    public ResponseEntity<ErrorResponse> handleGetUserCartException(GetUserCartException e) {
        logger.warn("GetUserCartException: " + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCartItemNotFoundException(CartItemNotFoundException e) {
        logger.warn("CartItemNotFoundException: " + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
