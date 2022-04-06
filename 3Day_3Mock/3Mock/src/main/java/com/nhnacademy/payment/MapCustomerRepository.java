package com.nhnacademy.payment;

import java.util.HashMap;

public class MapCustomerRepository implements CustomerRepository{
    private final HashMap<String, Customer> source = new HashMap<>();
    @Override
    public void addCustomer(Customer customer) {
        source.put(customer.getCustomerId(), customer);
    }

    @Override
    public Customer findById(String customerId) {
        return source.get(customerId);
    }
}
