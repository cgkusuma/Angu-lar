package com.demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DuplicateCustomerIDException.class)
	public ResponseEntity<ExceptionResponse> handleDuplicateIDException(DuplicateCustomerIDException e){
		ExceptionResponse response=new ExceptionResponse();
		response.setErrorCode("CONFLICT");
		response.setErrorMessage(e.getMessage());
		response.setTimestamp(LocalDateTime.now());
		ResponseEntity<ExceptionResponse> responseEntyity=new ResponseEntity<ExceptionResponse>(response,HttpStatus.CONFLICT);
		return responseEntyity;
	}
	
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getFieldErrors().forEach(error -> {
	            errors.put(error.getField(), error.getDefaultMessage());
	            //
	        });
	        return ResponseEntity.badRequest().body(errors);
	    }
	  @ExceptionHandler(InvalidCustomerIDException.class)
		public ResponseEntity<ExceptionResponse> InvalidCustomer(InvalidCustomerIDException e){
			ExceptionResponse response=new ExceptionResponse();
			response.setErrorCode("CONFLICT");
			response.setErrorMessage(e.getMessage());
			response.setTimestamp(LocalDateTime.now());
			ResponseEntity<ExceptionResponse> responseEntyity=new ResponseEntity<ExceptionResponse>(response,HttpStatus.CONFLICT);
			return responseEntyity;
		}
	  
	  @ExceptionHandler(CustomerNotFoundException.class)
		public ResponseEntity<ExceptionResponse> handleCustomerNotFoundException1(CustomerNotFoundException e){
			ExceptionResponse response=new ExceptionResponse();
			response.setErrorCode("CONFLICT");
			response.setErrorMessage(e.getMessage());
			response.setTimestamp(LocalDateTime.now());
			ResponseEntity<ExceptionResponse> responseEntyity=new ResponseEntity<ExceptionResponse>(response,HttpStatus.CONFLICT);
			return responseEntyity;
		}

	  
}
	        
	  
	  
	
	

