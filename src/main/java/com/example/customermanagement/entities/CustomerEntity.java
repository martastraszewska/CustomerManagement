package com.example.customermanagement.entities;

import com.example.customermanagement.dtos.CustomerDto;

import java.util.UUID;

public class CustomerEntity {

    private String id;
    private String firstName;
    private String lastName;
    private String company;

    private String city;
    private String street;
    private String phoneNumber;
    private String emailAddress;
    private String lastOverviewDate;

    public static CustomerEntity fromCustomerDto(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.id = (customerDto.getId() == null) ? UUID.randomUUID().toString() : customerDto.getId();
        customerEntity.firstName = customerDto.getFirstName();
        customerEntity.lastName = customerDto.getLastName();
      customerEntity.company = customerDto.getCompany();
        customerEntity.city = customerDto.getCity();
        customerEntity.street = customerDto.getStreet();
        customerEntity.phoneNumber = customerDto.getPhoneNumber();
        customerEntity.emailAddress = customerDto.getEmailAddress();
        customerEntity.lastOverviewDate = customerDto.getLastOverviewDate();
        return customerEntity;
    }
    public CustomerDto toCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(this.id);
        customerDto.setFirstName(this.firstName);
        customerDto.setLastName(this.lastName);
        customerDto.setCompany(this.company);
        customerDto.setCity(this.city);
        customerDto.setStreet(this.street);
        customerDto.setPhoneNumber(this.phoneNumber);
        customerDto.setEmailAddress(this.emailAddress);
        customerDto.setLastOverviewDate(this.lastOverviewDate);
        return customerDto;
    }

}
