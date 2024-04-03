package com.example.customermanagement.app.validators;

public class CustomerNotFoundException extends RuntimeException {


    public static final String CUSTOMER_NOT_FOUND_MSG = "Customer not found %s";

    public CustomerNotFoundException(String customerId) {
        super(String.format(CUSTOMER_NOT_FOUND_MSG, customerId));
    }
}
