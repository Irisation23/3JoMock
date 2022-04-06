package com.nhnacademy.payment;

public interface CustomerRepository {
    void addCustomer(Customer customer);
    Customer findById(String customerId);

}
