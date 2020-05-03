/*
 * After creating the database_sequence collections,
 * create a users collection, and a corresponding model object, 
 * that'll store the details of employees for our system:
 *  
 * */

package com.lele.empcrud.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employee")
public class Employee {
	
	@Transient // ObjectMapping to ignore field when persisting from a Java Object to JSON
	public static final String SEQUENCE_NAME = "employee_sequence";
	/*
	 * In this Employee model created, we added a static field SEQUENCE_NAME, 
	 * which is a unique reference to the auto-incremented sequence for the 
	 * employee collection.
	 * 
	 * We also annotate it with the @Transient to prevent it from being persisted 
	 * alongside other properties of the model.
	 * 
	 * */
	
	@Id
	private long id; // by default mongo creates a unique index on id field
	
	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)// create a unique index on firstName field
	private String firstName;
	private String lastName;
	
	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true) // create a unique index on emailId field
	private String emailId;
	
	
	// default constructor
	public Employee() {}
	
	// Parametrized constructor
	public Employee(String firstName, String lastName, String emailId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}

	// getters and setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + 
				", firstName=" + firstName + 
				", lastName=" + lastName + "]";
	}

}
