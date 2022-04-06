package com.nhnacademy.payment;

public class InvalidAmountException extends RuntimeException{
    public InvalidAmountException() {
        super("Pay Failed. Money is negative");
    }
}
