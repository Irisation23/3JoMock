package com.nhnacademy.payment;

public class Receipt {
    private String customerId;
    private long amount;

    public String getCustomerId() {
        return customerId;
    }

    public Receipt(long amount, String customerId){
        this.amount = amount;
        this.customerId = customerId;
    }

    public long getAmount() {
        return amount;
    }




}
