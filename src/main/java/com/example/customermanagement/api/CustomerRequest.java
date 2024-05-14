package com.example.customermanagement.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CustomerRequest {

    private String firstName;
    private String lastName;
    private String company;
    private String city;
    private String street;
    private String phoneNumber;
    private String emailAddress;
    private String lastOverviewDate;


}
