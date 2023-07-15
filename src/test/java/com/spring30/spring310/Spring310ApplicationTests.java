package com.spring30.spring310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// integration test - testing the whole application
// mockMVC - mock the http requests and responses

@SpringBootTest
@AutoConfigureMockMvc // auto configures the mockMVC
class Spring310ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ObjectMapper objectMapper; // converts java objects to json and vice versa

	@BeforeEach
	void setUp() {
			customerRepository.save(new Customer(1, "John", "Doe", "john@a.com", "12345678", "Manager", 1990));
		customerRepository.save(new Customer(2, "Tony", "Stark", "tony@a.com", "12345678", "Manager", 1990));
	}

	@Test
	void contextLoads() {

	}

	@Test
	void getCustomerByIdtest() throws Exception {
		// Arrange
		RequestBuilder request = MockMvcRequestBuilders.get("/customers/1");
		// Act
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.firstName").value("John"))
				.andExpect(jsonPath("$.lastName").value("Doe"));

	}

	@Test
	public void getAllCustomersTest() throws Exception {
		// Arrange
		RequestBuilder request = MockMvcRequestBuilders.get("/customers");
		// Act
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$[0].id").value(1)).andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].lastName").value("Doe")).andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].firstName").value("Tony")).andExpect(jsonPath("$[1].lastName").value("Stark"));

	}

	@Test
	public void createCustomerTest() throws Exception {
		// Arrange
		Customer newCustomer = new Customer(3, "Bruce", "Wayne", "email.com", "12345678", "Manager", 1990);

		String newCustomerAsJson = objectMapper.writeValueAsString(newCustomer);
		RequestBuilder request = MockMvcRequestBuilders.post("/customers").contentType("application/json")
				.content(newCustomerAsJson);

		// Act
		mockMvc.perform(request).andExpect(status().isCreated()).andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.id").value(3)).andExpect(jsonPath("$.firstName").value("Bruce"))
				.andExpect(jsonPath("$.lastName").value("Wayne"));

	}

	@Test
	public void invalidCreateCustomerTest() throws Exception {

		Customer invalidCustomer = new Customer(4, "", "Wayne", "email.com", "12345678", "Manager", 1990);

		String invalidCustomerAsJson = objectMapper.writeValueAsString(invalidCustomer);

		RequestBuilder request = MockMvcRequestBuilders.post("/customers").contentType("application/json")
				.content(invalidCustomerAsJson);

		mockMvc.perform(request).andExpect(status().isBadRequest());
	}

	}

