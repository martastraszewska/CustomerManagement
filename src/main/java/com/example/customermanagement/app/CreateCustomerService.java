package com.example.customermanagement.app;

import com.example.customermanagement.api.CustomerRequest;
import com.example.customermanagement.app.validators.InvalidCustomerException;
import com.example.customermanagement.app.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CreateCustomerService {

    @Autowired
    private CustomerStorage customerStorage;

    public Customer createCustomer(CustomerRequest request) {
        new Validator().validateCustomer(request);
        if (anyBlank(request.getFirstName(), request.getLastName()) && anyBlank(request.getCompany())) {
            throw new InvalidCustomerException("Customer has to have at least first and last name or company");
        }
        Customer customer = Customer.create(request);
        return customerStorage.store(customer);
    }

    private boolean anyBlank(String... args) {
        return Arrays.stream(args).anyMatch(value -> value == null || value.isBlank());
    }

}
