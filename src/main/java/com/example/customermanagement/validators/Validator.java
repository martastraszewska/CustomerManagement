package com.example.customermanagement.validators;

import com.example.customermanagement.exceptions.InvalidCustomerException;

import java.util.regex.Pattern;

public class Validator {

    public void validateEmail(String emailAddress, String regexPattern){
        regexPattern = "^(.+)@(\\S+)$";
        if (Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches()){

        }else {throw InvalidCustomerException}
}}
