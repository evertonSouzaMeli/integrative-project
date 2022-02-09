package com.meli.bootcamp.stock.exception;

public class InvalidEnumException extends RuntimeException {
    public InvalidEnumException() {
        super("Category sent as parameter does not exist! The valid categories are CONGELADO, REFRIGERADO and FRESH");
    }
}
