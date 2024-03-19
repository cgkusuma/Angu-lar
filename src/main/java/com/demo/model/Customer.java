package com.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customertbl")
public class Customer {


	@Id
	private int custId;
	
	//@Size(min = 3,message = "name should be atleast 3 characters")
	@NotBlank(message = "name cannot be blank")
	private String name;
	
	@NotBlank(message = "enter valid email")
	//@Email(message = " email id is invalid")
	private String email;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address")
	private Address address;
	
	

	
	
	public Customer(int custId, @NotBlank(message = "name cannot be blank") String name,
			@NotBlank(message = "email id is invalid") @Email String email) {
		super();
		this.custId = custId;
		this.name = name;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer(int custId, String name) {
		super();
		this.custId = custId;
		this.name = name;
	}
	
	public Customer() {
	
	}
	

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", name=" + name + ", EmailId= "+email+ "]";
	}
	
	

}
