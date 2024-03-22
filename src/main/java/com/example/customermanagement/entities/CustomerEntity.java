package com.example.customermanagement.entities;

import com.example.customermanagement.dtos.CustomerDto;

import java.util.UUID;

public class CustomerEntity {

    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String city;
    private String street;
    private String phoneNumber;
    private String lastOverviewDate;

    public static CustomerEntity fromCustomerDto(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.id = (customerDto.getId() == null) ? UUID.randomUUID().toString() : customerDto.getId();
        customerEntity.firstName = customerDto.getFirstName();
        customerEntity.lastName = customerDto.getLastName();
        customerEntity.emailAddress = customerDto.getEmailAddress();
        customerEntity.city = customerDto.getCity();
        customerEntity.street = customerDto.getStreet();
        customerEntity.phoneNumber = customerDto.getPhoneNumber();
        customerEntity.lastOverviewDate = customerDto.getLastOverviewDate();
        return customerEntity;
    }
    public CustomerDto toCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(this.id);
        customerDto.setFirstName(this.firstName);
        customerDto.setEmailAddress(this.emailAddress);
        customerDto.setCity(this.city);
        customerDto.setStreet(this.street);
        customerDto.setPhoneNumber(this.phoneNumber);
        customerDto.setLastOverviewDate(this.lastOverviewDate);
        return customerDto;
    }

}
