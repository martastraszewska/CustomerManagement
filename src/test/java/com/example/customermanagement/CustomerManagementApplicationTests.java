package com.example.customermanagement;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class CustomerManagementApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;
	@Test
	void contextLoads() {
	}

	@Test
	public void shouldReturnListOfCustomersWhenExistsByLastNameTest(){

	}
	@Test
	public void shouldReturnEmptyListWhenCustomerNotExistsByLastNameTest(){

	}
	@Test
	public void shouldReturnListOfCustomersWhenExistsByCityTest(){

	}
	@Test
	public void shouldReturnEmptyListWhenCustomerNotExistsByCityTest(){

	}
	@Test
	public void shouldReturnListOfCustomersWhenExistsByPhoneNumberTest(){

	}
	@Test
	public void shouldReturnListOfCustomersWhenNotExistsByPhoneNumberTest(){

	}
	@Test
	public void shouldAddCustomerWhenNotExistsTest(){

	}
	@Test
	public void shouldDeleteCustomerWhenExistsTest() {

	}
	@Test
	public void shouldReturn404OnDeleteWhenCustomerNotExistsTest(){

	}
	@Test
	public void shouldUpdateCustomerWhenExistsTest(){

	}
	@Test
	public void shouldReturn404OnUpdateWhenCustomerNotExistsTest(){

	}
	@Test
	public void shouldReturnListOfCustomersWhenQueryForNextOverviewTest(){

	}
}

