package com.example.customermanagement;

import com.example.customermanagement.api.CustomerRequest;
import com.example.customermanagement.api.CustomerResponse;
import com.example.customermanagement.api.ErrorResponse;
import com.example.customermanagement.app.Customer;
import com.example.customermanagement.infrastructure.InMemoryDB;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.List;

import static com.example.customermanagement.app.validators.CustomerNotFoundException.CUSTOMER_NOT_FOUND_MSG;
import static com.example.customermanagement.app.validators.Validator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class CustomerManagementApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private InMemoryDB customerStorage;

    @BeforeEach
    void before() {
        customerStorage.deleteAll();
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
        Assert.assertEquals(req.getCompany(), body.getCompany());
        Assert.assertEquals(req.getCity(), body.getCity());
        Assert.assertEquals(req.getStreet(), body.getStreet());
        Assert.assertEquals(req.getPhoneNumber(), body.getPhoneNumber());
        Assert.assertEquals(req.getEmailAddress(), body.getEmailAddress());
        Assert.assertEquals(req.getLastOverviewDate(), body.getLastOverviewDate());
        Assert.assertTrue(customerStorage.findById(body.getId()).get().getCreatedAt().isAfter(Instant.now().minusSeconds(15)));
        Assert.assertTrue(customerStorage.findById(body.getId()).get().getCreatedAt().isBefore(Instant.now().plusSeconds(1)));
    }

    @ParameterizedTest
    @MethodSource("testValidationInput")
    public void shouldReturnErrorMessageOnCreateWhenInvalidRequestOccursTest(CustomerRequest req, String errorMessage) {
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
                        CustomerRequestBuilder.builder().setCompany("dummyNamedummyNamedummyNamedummyNamedummyNamed").build(),
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
    public void shouldUpdateCustomerWhenExistsTest() {
        //given
        CustomerRequest req = CustomerRequestBuilder.builder().build();
        Customer existing = Customer.create(req);
        customerStorage.store(existing);
        //when
        CustomerRequest reqUpdate = CustomerRequestBuilder.builder().setPhoneNumber("newNumber").build();
        HttpEntity<CustomerRequest> request = new HttpEntity<>(reqUpdate, new HttpHeaders());
        ResponseEntity<CustomerResponse> res = this.testRestTemplate.exchange(
                "/api/v1/customers/" + existing.getId(), HttpMethod.PUT, request, CustomerResponse.class);
        CustomerResponse body = res.getBody();
        //then
        assertEquals(HttpStatus.OK, res.getStatusCode());
        //and
        assertEquals(1, customerStorage.size());
        //and
        assertEquals(existing.getId(), body.getId());
        assertEquals(existing.getFirstName(), body.getFirstName());
        assertEquals(existing.getLastName(), body.getLastName());
        assertEquals(existing.getCompany(), body.getCompany());
        assertEquals(existing.getCity(), body.getCity());
        assertEquals(existing.getStreet(), body.getStreet());
        assertEquals(existing.getLastOverviewDate(), body.getLastOverviewDate());
        assertEquals(reqUpdate.getPhoneNumber(), body.getPhoneNumber());
        Assert.assertTrue(customerStorage.findById(body.getId()).get().getUpdatedAt().isAfter(Instant.now().minusSeconds(15)));
        Assert.assertTrue(customerStorage.findById(body.getId()).get().getUpdatedAt().isBefore(Instant.now().plusSeconds(1)));
    }


    @Test
    public void shouldReturn404OnUpdateWhenCustomerNotExistsTest() {
        //when
        CustomerRequest req = CustomerRequestBuilder.builder().setPhoneNumber("newNumber").build();
        HttpEntity<CustomerRequest> request = new HttpEntity<>(req, new HttpHeaders());
        String customerId = "dummy-id";
        ResponseEntity<ErrorResponse> res = this.testRestTemplate.exchange(
                "/api/v1/customers/" + customerId, HttpMethod.PUT, request, ErrorResponse.class);
        //then
        assertEquals(404, res.getStatusCode().value());
        assertEquals(String.format(CUSTOMER_NOT_FOUND_MSG, customerId), res.getBody().getErrorMessage());
    }

    @Test
    public void shouldUpdateCustomerWithEmptyDataTest() {
        //given
        CustomerRequest req = CustomerRequestBuilder.builder().build();
        Customer existing = Customer.create(req);
        customerStorage.store(existing);
        //when
        CustomerRequest reqUpdate = CustomerRequestBuilder.builder().build();
        reqUpdate.setFirstName(null);
        reqUpdate.setLastName(null);
        reqUpdate.setCompany(null);
        reqUpdate.setCity(null);
        reqUpdate.setEmailAddress(null);
        reqUpdate.setStreet(null);
        reqUpdate.setPhoneNumber(null);
        reqUpdate.setLastOverviewDate(null);
        HttpEntity<CustomerRequest> request = new HttpEntity<>(reqUpdate, new HttpHeaders());
        ResponseEntity<CustomerResponse> res = this.testRestTemplate.exchange(
                "/api/v1/customers/" + existing.getId(), HttpMethod.PUT, request, CustomerResponse.class);
        CustomerResponse body = res.getBody();
        //then
        assertEquals(HttpStatus.OK, res.getStatusCode());
        //and
        assertEquals(1, customerStorage.size());
        //and
        assertEquals(existing.getId(), body.getId());
        assertEquals(existing.getFirstName(), body.getFirstName());
        assertEquals(existing.getLastName(), body.getLastName());
        assertEquals(existing.getCompany(), body.getCompany());
        assertEquals(existing.getCity(), body.getCity());
        assertEquals(existing.getStreet(), body.getStreet());
        assertEquals(existing.getLastOverviewDate(), body.getLastOverviewDate());
        assertEquals(reqUpdate.getPhoneNumber(), body.getPhoneNumber());
        Assert.assertTrue(customerStorage.findById(body.getId()).get().getUpdatedAt().isAfter(Instant.now().minusSeconds(15)));
        Assert.assertTrue(customerStorage.findById(body.getId()).get().getUpdatedAt().isBefore(Instant.now().plusSeconds(1)));
    }

    @ParameterizedTest
    @MethodSource("testValidationInput")
    public void shouldReturnErrorMessageOnUpdateWhenInvalidRequestOccursTest(CustomerRequest req, String errorMessage) {
        //given
        CustomerRequest reqExisting = CustomerRequestBuilder.builder().build();
        Customer existing = Customer.create(reqExisting);
        customerStorage.store(existing);
        //when
        HttpEntity<CustomerRequest> request = new HttpEntity<>(req, new HttpHeaders());
        ResponseEntity<ErrorResponse> res = this.testRestTemplate.exchange
                ("/api/v1/customers/" + existing.getId(), HttpMethod.PUT, request, ErrorResponse.class);
        //then
        Assert.assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
        Assert.assertEquals(errorMessage, res.getBody().getErrorMessage());
    }


    @Test
    public void shouldDeleteCustomerWhenExistsTest() {
        //given
        CustomerRequest reqExisting = CustomerRequestBuilder.builder().build();
        Customer existing = Customer.create(reqExisting);
        customerStorage.store(existing);
        //when
        ResponseEntity<Void> res = this.testRestTemplate.exchange
                ("/api/v1/customers/" + existing.getId(), HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        //then
        assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
        //and
        assertEquals(0, customerStorage.size());

    }

    @Test
    public void shouldReturn404OnDeleteWhenCustomerNotExistsTest() {
        //when
        String customerId = "dummy-id";
        ResponseEntity<ErrorResponse> res = this.testRestTemplate.exchange
                ("/api/v1/customers/" + customerId, HttpMethod.DELETE, HttpEntity.EMPTY, ErrorResponse.class);
        //then
        assertEquals(404, res.getStatusCode().value());
        //and
        assertEquals(String.format(CUSTOMER_NOT_FOUND_MSG, customerId), res.getBody().getErrorMessage());
    }

}

