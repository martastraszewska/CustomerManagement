package com.example.customermanagement.app;

import com.example.customermanagement.api.CustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
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
    private String company;
    private String city;
    private String street;
    private String phoneNumber;
    private String emailAddress;
    private String lastOverviewDate;
    private Instant createdAt;
    private Instant updatedAt;


    public static Customer create(CustomerRequest request) {
        return Customer.builder()
                .id(UUID.randomUUID().toString())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .company(request.getCompany())
                .city(request.getCity())
                .street(request.getStreet())
                .phoneNumber(request.getPhoneNumber())
                .emailAddress(request.getEmailAddress())
                .lastOverviewDate(request.getLastOverviewDate())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public Customer update(CustomerRequest request) {
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.company = request.getCompany();
        this.city = request.getCity();
        this.street = request.getStreet();
        this.phoneNumber = request.getPhoneNumber();
        this.emailAddress = request.getEmailAddress();
        this.lastOverviewDate = request.getLastOverviewDate();
        this.updatedAt = Instant.now();
        return this;
    }
}
