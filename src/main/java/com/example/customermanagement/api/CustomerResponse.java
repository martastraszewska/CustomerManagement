package com.example.customermanagement.api;

import com.example.customermanagement.app.Customer;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String company;
    private String city;
    private String street;
    private String phoneNumber;
    private String emailAddress;
    private String lastOverviewDate;

    public static CustomerResponse from(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .company(customer.getCompany())
                .city(customer.getCity())
                .street(customer.getStreet())
                .phoneNumber(customer.getPhoneNumber())
                .emailAddress(customer.getEmailAddress())
                .lastOverviewDate(customer.getLastOverviewDate())
                .build();
    }

}
