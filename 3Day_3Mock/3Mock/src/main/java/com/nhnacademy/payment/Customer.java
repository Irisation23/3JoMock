package com.nhnacademy.payment;

public class Customer {
    private String customerId;
    private String phoneNumber;
    private long money;
    private long point;

    public Customer(String customerId, long money, String phoneNumber) {
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.money = money;
        this.point = 0;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getPhone() {
        return phoneNumber;
    }

    public long getMoney() {
        return money;
    }

    public long getPoint() {
        return point;
    }

    public void payMoney(long money) {
        this.money -= money;
    }

    public void earnPoint(long accuredAmount) {
        this.point += accuredAmount;
    }

}
