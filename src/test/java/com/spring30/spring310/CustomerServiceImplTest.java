package com.spring30.spring310;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CustomerServiceImplTest {

    @Mock    
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;
    
    @Test
    public void createCustomerTest() {
       
        // Arrange
        Customer customer = new Customer(1, "John", "Doe", "123@email.com", "12345",
                "Developer", 1990);

        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        Customer savedCustomer = customerService.createCustomer(customer);

        // Assert
        verify(customerRepository, times(1)).save(customer);
        assertEquals(customer, savedCustomer);
    }

    @Test
    public void getAllCustomersTest() {
        // Arrange
        when(customerRepository.findAll()).thenReturn(Arrays.asList(
            new Customer(1, "John", "Doe", "123@email.com", "12345",
                "Developer", 1990),
            new Customer(2, "James", "Cewa", "1234@email.com", "123456",
                "Developer", 1990)
        ));

        // Act
        List<Customer> allCustomers = customerService.getAllCustomers();

        // Assert
        assertEquals(2, allCustomers.size());

    }

    @Test
    public void getCustomerTest() {
        // Arrange
        Customer customer =  new Customer(1, "John", "Doe", "123@email.com", "12345",
        "Developer", 1990);

        when(customerRepository.findById(1)).thenReturn(java.util.Optional.of(customer));

        // Act
        Customer foundCustomer = customerService.getCustomer(1);

        // Assert
        assertEquals(customer, foundCustomer);

    }

}
