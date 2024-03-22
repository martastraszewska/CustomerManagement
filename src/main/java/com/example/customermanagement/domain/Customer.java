package com.example.customermanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String city;
    private String street;
    private String phoneNumber;
    private String lastOverviewDate;


}
