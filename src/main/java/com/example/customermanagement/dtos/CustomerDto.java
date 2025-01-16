package com.example.customermanagement.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String company;
    private String city;
    private String street;
    private String phoneNumber;
    private String emailAddress;
    private String lastOverviewDate;
}
