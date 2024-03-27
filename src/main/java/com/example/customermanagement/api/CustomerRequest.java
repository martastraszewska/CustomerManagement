package com.example.customermanagement.api;

import com.example.customermanagement.app.Customer;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CustomerRequest {

    private String firstName;
    private String lastName;
    private String companyName;
    private String emailAddress;
    private String city;
    private String street;
    private String phoneNumber;
    private String lastOverviewDate;



}
