package com.nhnacademy.payment;

public class UnknownIdException extends RuntimeException{
    public UnknownIdException(String customerId) {
        super("Pay Failed. Unknown Id" + customerId);
    }
}
