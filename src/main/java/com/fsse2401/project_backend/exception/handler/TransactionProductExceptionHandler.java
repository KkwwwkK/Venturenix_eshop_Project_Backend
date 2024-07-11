package com.fsse2401.project_backend.exception.handler;

import com.fsse2401.project_backend.exception.response.ErrorResponse;
import com.fsse2401.project_backend.exception.transactionProduct.TransactionNotFoundException;
import com.fsse2401.project_backend.service.impl.TransactionProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransactionProductExceptionHandler {
    Logger logger = LoggerFactory.getLogger(TransactionProductServiceImpl.class);


}
