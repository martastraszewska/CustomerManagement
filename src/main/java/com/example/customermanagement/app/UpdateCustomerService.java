package com.example.customermanagement.app;

import com.example.customermanagement.api.CustomerRequest;
import com.example.customermanagement.app.validators.CustomerNotFoundException;
import com.example.customermanagement.app.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateCustomerService {
    @Autowired
    private CustomerStorage customerStorage;
    @Autowired
    private CustomerReader customerReader;

    public Customer updateCustomer(String id, CustomerRequest request) {
        Optional<Customer> optionalCustomer = customerReader.findById(id);
        if (optionalCustomer.isPresent()) {
            new Validator().validateCustomer(request);
            Customer customer = optionalCustomer.get().update(request);
            return customerStorage.store(customer);
        } else {
            throw new CustomerNotFoundException(id);
        }
    }
}
