package com.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.demo.dao.CustomerRepository;
import com.demo.exception.DuplicateCustomerIDException;
import com.demo.model.Customer;
import com.demo.service.CustomerServiceImp;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImp customerService;

	@Test
	public void testShowCustomer() {
		// Given
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(101, "priya"));
		customers.add(new Customer(102, "reena"));
		customers.add(new Customer(103, "preeti"));
		when(customerRepository.findAll()).thenReturn(customers);

		// When
		List<Customer> result = customerService.showCustomer();

		// Then
		assertEquals(3, result.size());
		assertEquals("priya", result.get(0).getName());
		assertEquals("reena", result.get(1).getName());
		assertEquals("preeti", result.get(2).getName());
		verify(customerRepository).findAll();
	}

	@Test
	public void testAddCustomer() throws DuplicateCustomerIDException {
		// Given
		Customer customer = new Customer(105, "swati");
		when(customerRepository.findById(105)).thenReturn(Optional.empty());
		when(customerRepository.saveAndFlush(customer)).thenReturn(customer);

		// When
		int id = customerService.addCustomer(customer);

		// Then
		assertEquals(105, id);
		verify(customerRepository).findById(105);
		verify(customerRepository).saveAndFlush(customer);
	}

	@Test
	public void testAddCustomerWithDuplicateId() {
		// Given
		Customer existingCustomer = new Customer(101, "priya");
		when(customerRepository.findById(101)).thenReturn(Optional.of(existingCustomer));

		// Then
		assertThrows(DuplicateCustomerIDException.class, () -> {
			// When
			customerService.addCustomer(existingCustomer);
		});

		// Verify that saveAndFlush method is not called
		verify(customerRepository, never()).saveAndFlush(existingCustomer);
	}

	@Test
	public void testDeleteCustomerById() {
		// Given
		int customerId = 101;

		// When
		customerService.deleteCustomerByID(customerId);

		// Then
		verify(customerRepository).deleteById(customerId);
	}
}
