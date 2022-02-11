package com.meli.bootcamp.shipment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private HttpStatus httpStatus = null;

    public BusinessException(String message) {
        super(message.toUpperCase());
    }

    public BusinessException(String message, HttpStatus httpStatus){
        this(message);
        this.httpStatus = httpStatus;
    }

}
