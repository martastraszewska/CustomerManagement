package com.example.customermanagement;

import com.example.customermanagement.api.CustomerRequest;
import com.example.customermanagement.api.CustomerResponse;
import com.example.customermanagement.api.ErrorResponse;
import com.example.customermanagement.app.Customer;
import com.example.customermanagement.app.validators.Validator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.List;

import static com.example.customermanagement.app.validators.Validator.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class CustomerManagementApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void shouldReturnListOfCustomersWhenExistsByLastNameTest() {

    }

    @Test
    public void shouldReturnEmptyListWhenCustomerNotExistsByLastNameTest() {

    }

    @Test
    public void shouldReturnListOfCustomersWhenExistsByCityTest() {

    }

    @Test
    public void shouldReturnEmptyListWhenCustomerNotExistsByCityTest() {

    }

    @Test
    public void shouldReturnListOfCustomersWhenExistsByPhoneNumberTest() {

    }

    @Test
    public void shouldReturnListOfCustomersWhenNotExistsByPhoneNumberTest() {

    }

    @Test
    public void shouldAddCustomerWhenNotExistsTest() {
        //when
        CustomerRequest req = CustomerRequestBuilder.builder().build();
        HttpEntity<CustomerRequest> request = new HttpEntity<>(req, new HttpHeaders());
        ResponseEntity<CustomerResponse> res = this.testRestTemplate.postForEntity("/api/v1/customers", request, CustomerResponse.class);
        CustomerResponse body = res.getBody();
        //then
        Assert.assertEquals("/api/v1/customers/" + body.getId(), res.getHeaders().getLocation().toString());
        Assert.assertEquals(201, res.getStatusCode().value());
        Assert.assertEquals(req.getFirstName(), body.getFirstName());
        Assert.assertEquals(req.getLastName(), body.getLastName());
        Assert.assertEquals(req.getEmailAddress(), body.getEmailAddress());
        Assert.assertEquals(req.getCity(), body.getCity());
        Assert.assertEquals(req.getStreet(), body.getStreet());
        Assert.assertEquals(req.getPhoneNumber(), body.getPhoneNumber());
        Assert.assertEquals(req.getLastOverviewDate(), body.getLastOverviewDate());
    }

    @ParameterizedTest
    @MethodSource("testValidationInput")
    public void shouldReturnErrorMessageWhenInvalidRequestOccursTest(CustomerRequest req, String errorMessage) {
        //when
        HttpEntity<CustomerRequest> request = new HttpEntity<>(req, new HttpHeaders());
        ResponseEntity<ErrorResponse> res = this.testRestTemplate.postForEntity("/api/v1/customers", request, ErrorResponse.class);
        //then
        Assert.assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
        Assert.assertEquals(errorMessage, res.getBody().getErrorMessage());
    }
    public static List<Arguments> testValidationInput() {
        return List.of(
                Arguments.of(
                        CustomerRequestBuilder.builder().setFirstName("dummyNamedummyNamedummyNamedummyNamedummyNamed").build(),
                        FIRST_NAME_CANNOT_BE_LONGER_THAN_45_CHARACTERS),
                Arguments.of(
                        CustomerRequestBuilder.builder().setLastName("dummyNamedummyNamedummyNamedummyNamedummyNamed").build(),
                        LAST_NAME_CANNOT_BE_LONGER_THAN_45_CHARACTERS),
                Arguments.of(
                        CustomerRequestBuilder.builder().setCompanyName("dummyNamedummyNamedummyNamedummyNamedummyNamed").build(),
                        COMPANY_NAME_CANNOT_BE_LONGER_THAN_45_CHARACTERS),
                Arguments.of(
                        CustomerRequestBuilder.builder().setEmailAddress("dummy-email").build(),
                        INVALID_ADDRESS_EMAIL_PATTERN),
                Arguments.of(
                        CustomerRequestBuilder.builder().setCity("dummyNamedummyNamedummyNamedummyNamedummyNamed").build(),
                        CITY_CANNOT_BE_LONGER_THAN_45_CHARACTERS),
                Arguments.of(
                        CustomerRequestBuilder.builder().setStreet("dummyNamedummyNamedummyNamedummyNamedummyNamed").build(),
                        STREET_CANNOT_BE_LONGER_THAN_45_CHARACTERS),
                Arguments.of(
                        CustomerRequestBuilder.builder().setPhoneNumber("1234567890").build(),
                        PHONE_NUMBER_CANNOT_BE_LONGER_THAN_9_CHARACTERS),
                Arguments.of(
                        CustomerRequestBuilder.builder().setLastOverviewDate("01.01.2111").build(),
                        INVALID_DATE_PATTERN)
        );
    }


    @Test
    public void shouldDeleteCustomerWhenExistsTest() {

    }

    @Test
    public void shouldReturn404OnDeleteWhenCustomerNotExistsTest() {

    }

    @Test
    public void shouldUpdateCustomerWhenExistsTest() {

    }

    @Test
    public void shouldReturn404OnUpdateWhenCustomerNotExistsTest() {

    }

    @Test
    public void shouldReturnListOfCustomersWhenQueryForNextOverviewTest() {

    }

}

