package com.demo.exception;

public class DuplicateCustomerIDException extends Exception {

	public DuplicateCustomerIDException(String message){
		super(message);
	}
}
