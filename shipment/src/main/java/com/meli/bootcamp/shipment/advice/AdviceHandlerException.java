package com.meli.bootcamp.shipment.advice;

import com.meli.bootcamp.shipment.exception.BusinessException;
import com.meli.bootcamp.shipment.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;
import java.util.Date;

@RestControllerAdvice
public class AdviceHandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<AppErrorResponse> handleNotFoundException(NotFoundException notFoundException) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        AppErrorResponse errorResponse = new AppErrorResponse(
                Date.from(Instant.now()),
                notFound.value(),
                notFound.name(),
                notFoundException.getMessage(),
                null);

        return new ResponseEntity<>(errorResponse, notFound);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<AppErrorResponse> handleBusinessException(BusinessException businessException) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        AppErrorResponse errorResponse = new AppErrorResponse(
                Date.from(Instant.now()),
                badRequest.value(),
                badRequest.name(),
                businessException.getMessage(),
                null);

        return new ResponseEntity<>(errorResponse, badRequest);
    }
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<AppErrorResponse> handleBusinessException(HttpClientErrorException httpClientErrorException) {
        AppErrorResponse errorResponse = new AppErrorResponse(
                Date.from(Instant.now()),
                httpClientErrorException.getRawStatusCode(),
                httpClientErrorException.getStatusCode().name(),
                httpClientErrorException.getMessage(),
                null);
        return new ResponseEntity<>(errorResponse, httpClientErrorException.getStatusCode());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorResponse> handleBusinessException(MethodArgumentNotValidException methodArgumentNotValidException) {
        HttpStatus errorCode = HttpStatus.BAD_REQUEST;
        AppErrorResponse errorResponse = new AppErrorResponse(
                Date.from(Instant.now()),
                errorCode.value(),
                errorCode.name(),
                methodArgumentNotValidException.getLocalizedMessage(),
                null);
        return new ResponseEntity<>(errorResponse, errorCode);
    }

}
