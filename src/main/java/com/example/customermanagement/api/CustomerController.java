package com.example.customermanagement.api;

import com.example.customermanagement.app.CreateCustomerService;
import com.example.customermanagement.app.Customer;
import com.example.customermanagement.app.CustomerReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    CreateCustomerService createCustomerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        Customer customer = createCustomerService.createCustomer(customerRequest);
        return ResponseEntity
                .created(URI.create("/api/v1/customers/" + customer.getId()))
                .body(CustomerResponse.from(customer));
    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable String id, @RequestBody CustomerRequest customerRequest) {
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable String id) {
    }

    @GetMapping("/{id}")
    public CustomerResponse findCustomerById(@PathVariable String id) {
        return null;
    }
}
