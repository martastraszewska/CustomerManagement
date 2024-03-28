package com.example.customermanagement.api;

import com.example.customermanagement.app.CreateCustomerService;
import com.example.customermanagement.app.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CreateCustomerService createCustomerService;

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
