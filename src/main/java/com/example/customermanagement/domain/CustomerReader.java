package com.example.customermanagement.domain;

import java.util.Optional;

public interface CustomerReader {
    public Optional<Customer> findById(Customer customer);
}
