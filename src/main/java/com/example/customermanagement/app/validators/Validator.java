package com.example.customermanagement.app.validators;

import com.example.customermanagement.api.CustomerRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class Validator {

    public static final String FIRST_NAME_CANNOT_BE_LONGER_THAN_45_CHARACTERS =
            "First name cannot be longer than 45 characters.";
    public static final String LAST_NAME_CANNOT_BE_LONGER_THAN_45_CHARACTERS =
            "Last name cannot be longer than 45 characters.";
    public static final String INVALID_ADDRESS_EMAIL_PATTERN =
            "Invalid address email pattern.";
    public static final String CITY_CANNOT_BE_LONGER_THAN_45_CHARACTERS =
            "City cannot be longer than 45 characters.";
    public static final String STREET_CANNOT_BE_LONGER_THAN_45_CHARACTERS =
            "Street cannot be longer than 45 characters.";
    public static final String PHONE_NUMBER_CANNOT_BE_LONGER_THAN_9_CHARACTERS =
            "Phone number cannot be longer than 9 characters.";
    public static final String INVALID_DATE_PATTERN =
            "Invalid date pattern.";
    public static final String COMPANY_NAME_CANNOT_BE_LONGER_THAN_45_CHARACTERS =
            "Company name cannot be longer than 45 characters.";
    private Pattern pattern;
    private SimpleDateFormat dateFormat;

    public Validator() {
        this.pattern = Pattern.compile("^(.+)@(\\S+)$");
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    public void validateCustomer(CustomerRequest request) {


        throwIf(isNotBlank(request.getFirstName()) && request.getFirstName().length() > 45,
                FIRST_NAME_CANNOT_BE_LONGER_THAN_45_CHARACTERS);
        throwIf(isNotBlank(request.getLastName()) && request.getLastName().length() > 45,
                LAST_NAME_CANNOT_BE_LONGER_THAN_45_CHARACTERS);
        throwIf(isNotBlank(request.getCompany()) && request.getCompany().length() > 45,
                COMPANY_NAME_CANNOT_BE_LONGER_THAN_45_CHARACTERS);
        throwIf(isNotBlank(request.getEmailAddress()) && !pattern.matcher(request.getEmailAddress()).matches(),
                INVALID_ADDRESS_EMAIL_PATTERN);
        throwIf(isNotBlank(request.getCity())  && request.getCity().length() > 45,
                CITY_CANNOT_BE_LONGER_THAN_45_CHARACTERS);
        throwIf(isNotBlank(request.getStreet()) && request.getStreet().length() > 45,
                STREET_CANNOT_BE_LONGER_THAN_45_CHARACTERS);
        throwIf(isNotBlank(request.getPhoneNumber()) && request.getPhoneNumber().length() > 9,
                PHONE_NUMBER_CANNOT_BE_LONGER_THAN_9_CHARACTERS);
        validateDate(request.getLastOverviewDate(), INVALID_DATE_PATTERN);
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.isBlank();
    }



    private void validateDate(String date, String msg) {
        try {
            if (isNotBlank(date)) {
                dateFormat.parse(date);
            }
        } catch (ParseException e) {
            throw new InvalidCustomerException(msg);
        }
    }

    private void throwIf(boolean condition, String msg) {
        if (condition) {
            throw new InvalidCustomerException(msg);
        }
    }
}
