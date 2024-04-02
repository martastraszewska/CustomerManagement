package com.example.customermanagement.app;

import com.example.customermanagement.app.validators.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomerService {
    @Autowired
    private CustomerReader customerReader;
    @Autowired
    private CustomerRemover customerRemover;

    public void deleteCustomer(String id) {
        if (customerReader.findById(id).isPresent()) {
            customerRemover.deleteCustomer(id);
        } else {
            throw new CustomerNotFoundException(id);
        }
    }
}
