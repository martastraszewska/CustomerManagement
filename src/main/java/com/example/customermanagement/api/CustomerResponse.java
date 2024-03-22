package com.example.customermanagement.api;

import com.example.customermanagement.domain.Customer;
import lombok.Builder;

@Builder
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String city;
    private String street;
    private String phoneNumber;
    private String lastOverviewDate;

    public static CustomerResponse from(Customer customer){
            return CustomerResponse.builder()
                    .id(customer.getId())
                    .firstName(customer.getFirstName())
                    .emailAddress(customer.getEmailAddress())
                    .city(customer.getCity())
                    .street(customer.getStreet())
                    .phoneNumber(customer.getPhoneNumber())
                    .lastOverviewDate(customer.getLastOverviewDate())
                    .build();
        }

}
