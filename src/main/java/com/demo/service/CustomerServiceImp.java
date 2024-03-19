package com.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.demo.dao.CustomerRepository;
import com.demo.exception.CustomerNotFoundException;
import com.demo.exception.DuplicateCustomerIDException;
import com.demo.model.Customer;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	CustomerRepository custDao;
	
	
	@Override
	public List<Customer> showCustomer() {
		// TODO Auto-generated method stub
		System.out.println("Service layer Customer called");
		return custDao.findAll();
	}


	@Transactional
	@Override
	public int addCustomer(Customer c) throws DuplicateCustomerIDException {
		Optional<Customer> cust=custDao.findById(c.getCustId());
		//System.out.println(cust.get());
		//Customer c2=cust.get();
		//System.out.println("customer "+c2);
		if(cust.isPresent()) {			
				throw new DuplicateCustomerIDException("Customer with ID"+c.getCustId()+" already Exists");
			}
		custDao.saveAndFlush(c);
		return c.getCustId();
	}
	
	
		
		

	@Transactional
	@Override
	public Customer updateCustomer(Customer c) {
		
		Optional<Customer> previouCust= custDao.findById(c.getCustId());//Enity is in persistence state
		Customer cupdated=previouCust.get();
		//Do not change the customer id using setter method
		cupdated.setName(c.getName()); //automatically update data in table
	
		return cupdated;
	}


	@Override
	@Transactional
	public void deleteCustomerByID(int custID) {
		custDao.deleteById(custID);
		
	}


	@Override
	@Transactional
		public Customer findCustomerByID(int custID) throws CustomerNotFoundException {
			Optional<Customer> cut=custDao.findById(custID);
			if(!(cut.isPresent())) {
				throw new CustomerNotFoundException("Not Found");
			}
			return cut.get();
 
		}
 

}


	
	



	
		
	


