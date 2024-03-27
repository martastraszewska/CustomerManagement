package com.example.customermanagement.app;

import com.example.customermanagement.api.CustomerRequest;
import com.example.customermanagement.app.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerService {

    @Autowired
    private  CustomerStorage customerStorage;

    public Customer createCustomer(CustomerRequest request){
        new Validator().validateCustomer(request);
        Customer customer = Customer.create(request);
        return customerStorage.store(customer);
    }

}
