package com.nhnacademy.payment;

public class SmsDummy {
    private String phoneNumber;

    public SmsDummy(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSuccess() {
        return !phoneNumber.isEmpty();
    }
}
