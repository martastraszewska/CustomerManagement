package com.example.customermanagement.app;

import java.util.List;
import java.util.Optional;

public interface CustomerReader {
    Optional<Customer> findById(String id);

    List<Customer> findAll();
}
