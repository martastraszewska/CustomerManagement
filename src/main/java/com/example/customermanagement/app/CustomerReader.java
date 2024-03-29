package com.example.customermanagement.app;

import java.util.Optional;

public interface CustomerReader {
    Optional<Customer> findById(String id);
}
