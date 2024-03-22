package com.example.customermanagement.api;

import com.example.customermanagement.domain.CreateCustomerService;
import com.example.customermanagement.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController("api/v1/customers")
public class CustomerController {

    @Autowired
    CreateCustomerService createCustomerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest) {
        Customer customer = createCustomerService.createCustomer(customerRequest);
        return CustomerResponse.from(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable String id) {
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse updateCustomer(@PathVariable String id, @RequestBody CustomerRequest customerRequest) {
        return null;
    }

    @GetMapping("/{id}")
    public CustomerResponse findCustomerById(@PathVariable String id) {
        return null;
    }
}
