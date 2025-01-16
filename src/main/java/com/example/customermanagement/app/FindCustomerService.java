package com.example.customermanagement.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindCustomerService {

    @Autowired
    private CustomerReader customerReader;

    public List<Customer> findAll() {
        return customerReader.findAll();
    }
}
