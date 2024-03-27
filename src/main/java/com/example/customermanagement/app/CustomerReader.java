package com.example.customermanagement.app;

import java.util.Optional;

public interface CustomerReader {
    public Optional<Customer> findById(Customer customer);
}
