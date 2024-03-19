package com.demo.service;

import java.util.List;

import com.demo.exception.CustomerNotFoundException;
import com.demo.exception.DuplicateCustomerIDException;
import com.demo.model.Customer;

public interface CustomerService {
	
	List<Customer> showCustomer();
	
	int addCustomer(Customer c) throws DuplicateCustomerIDException;
	Customer findCustomerByID(int custID) throws CustomerNotFoundException;
	
	Customer updateCustomer(Customer c);
	
	void deleteCustomerByID(int custID);

}
