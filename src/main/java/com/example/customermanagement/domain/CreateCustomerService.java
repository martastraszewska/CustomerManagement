package com.example.customermanagement.domain;

import com.example.customermanagement.api.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerService {

    @Autowired
    private  CustomerStorage customerStorage;

    public Customer createCustomer(CustomerRequest customerRequest){
        Customer customer = customerRequest.toCustomer();
        return customerStorage.store(customer);
    }

}
