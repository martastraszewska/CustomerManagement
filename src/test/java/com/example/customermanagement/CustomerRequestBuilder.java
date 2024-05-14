package com.example.customermanagement;
import com.example.customermanagement.api.CustomerRequest;
public class CustomerRequestBuilder {
    private String firstName = "firstName";
    private String lastName = "lastName";
    private String company = "companyName";

    private String city = "city";
    private String street = "street";
    private String phoneNumber = "123456789";
    private String emailAddress = "email@Address";
    private String lastOverviewDate = "01/01/2000";
    private CustomerRequestBuilder() {
    }
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getCompany() {return this.company; }



    public String getCity() {
        return this.city;
    }

    public String getStreet() {
        return this.street;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getLastOverviewDate() {
        return this.lastOverviewDate;
    }

    public CustomerRequestBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public CustomerRequestBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public CustomerRequestBuilder setCompany(String company) {
        this.company = company;
        return this;
    }


    public CustomerRequestBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public CustomerRequestBuilder setStreet(String street) {
        this.street = street;
        return this;
    }
    public CustomerRequestBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    public CustomerRequestBuilder setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public CustomerRequestBuilder setLastOverviewDate(String lastOverviewDate) {
        this.lastOverviewDate = lastOverviewDate;
        return this;
    }
    public static CustomerRequestBuilder builder() {
        return new CustomerRequestBuilder();
    }    public CustomerRequest build() {
        return new CustomerRequest(firstName, lastName, company,  city, street, phoneNumber, emailAddress, lastOverviewDate);
    }
}
