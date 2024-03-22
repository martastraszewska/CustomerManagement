package com.example.customermanagement.api;

import com.example.customermanagement.domain.Customer;

public class CustomerRequest {
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String city;
    private String street;
    private String phoneNumber;
    private String lastOverviewDate;

    public Customer toCustomer() {
        return Customer.builder()
                .id(this.id)
                .firstName(this.firstName)
                .emailAddress(this.emailAddress)
                .city(this.city)
                .street(this.street)
                .phoneNumber(this.phoneNumber)
                .lastOverviewDate(this.lastOverviewDate)
                .build();
    }

}
