package com.example.customermanagement.app;

import com.example.customermanagement.api.CustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
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
    private String companyName;
    private String emailAddress;
    private String city;
    private String street;
    private String phoneNumber;
    private String lastOverviewDate;
    private Instant createdAt;
    private Instant updatedAt;


    public static Customer create(CustomerRequest request) {
        return Customer.builder()
                .id(UUID.randomUUID().toString())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .companyName(request.getCompanyName())
                .emailAddress(request.getEmailAddress())
                .city(request.getCity())
                .street(request.getStreet())
                .phoneNumber(request.getPhoneNumber())
                .lastOverviewDate(request.getLastOverviewDate())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public Customer update(CustomerRequest request) {
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.companyName = request.getCompanyName();
        this.emailAddress = request.getEmailAddress();
        this.city = request.getCity();
        this.street = request.getStreet();
        this.phoneNumber = request.getPhoneNumber();
        this.lastOverviewDate = request.getLastOverviewDate();
        this.updatedAt = Instant.now();
        return this;
    }
}
