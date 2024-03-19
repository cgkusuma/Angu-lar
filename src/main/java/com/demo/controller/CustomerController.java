package com.demo.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.CustomerNotFoundException;
import com.demo.exception.DuplicateCustomerIDException;
import com.demo.exception.InvalidCustomerIDException;
import com.demo.model.Customer;
import com.demo.service.CustomerService;
 
@RestController
@Validated
@RequestMapping(value = "/customer")
public class CustomerController {
	@Autowired
	CustomerService custService;
	@GetMapping(produces = "application/json")
	List<Customer> showCustomer(){
		System.out.println("Customer Controller");
		List<Customer> custList=custService.showCustomer();
		return custList;
	}

 
	
	@PostMapping(consumes = "application/json",produces = "application/json")
	ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer cust) throws DuplicateCustomerIDException, InvalidCustomerIDException{
	   if(cust.getCustId()<0)
	   {
		   throw new InvalidCustomerIDException("Customer Id is invalid");
	   }
		int custId=custService.addCustomer(cust);
		if(custId==0) {
			throw new DuplicateCustomerIDException("Customer with ID"+cust.getCustId()+" already Exists");
		}
		System.out.println("Customer ID in controller is "+custId);		
		return ResponseEntity.ok(cust);
	}
	
	@GetMapping("/{custId}")
	ResponseEntity<Object> findCustomer(@PathVariable("custId") Integer custId) throws CustomerNotFoundException{
		custService.findCustomerByID(custId);
		if(custId==null) {
			throw new CustomerNotFoundException("NOT FOUND");
		}
		return new ResponseEntity<Object>("Customer with" +custId+" is found",HttpStatus.OK);
		}
		
	

	@PutMapping(consumes = "application/json",produces = "application/json")
	ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer cust){
		Customer c=custService.updateCustomer(cust);
		System.out.println("Customer ID in controller");		
		return new ResponseEntity<Customer>(c,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/{custId}")
	ResponseEntity<Object> deleteCustomer(@PathVariable("custId") Integer custId  ) {
		custService.deleteCustomerByID(custId);
		return new ResponseEntity<Object>("Customer with "+custId+" is deleted",HttpStatus.OK);
	}
}

