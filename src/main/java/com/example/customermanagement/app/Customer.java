package com.example.customermanagement.app;

import com.example.customermanagement.api.CustomerRequest;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String city;
    private String street;
    private String phoneNumber;
    private String lastOverviewDate;


    public static Customer create(CustomerRequest request) {
        return Customer.builder()
                .id(UUID.randomUUID().toString())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .emailAddress(request.getEmailAddress())
                .city(request.getCity())
                .street(request.getStreet())
                .phoneNumber(request.getPhoneNumber())
                .lastOverviewDate(request.getLastOverviewDate())
                .build();
    }

}
