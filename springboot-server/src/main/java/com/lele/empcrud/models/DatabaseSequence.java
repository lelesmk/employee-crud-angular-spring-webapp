/* 
 * When we're using MongoDB as the database for a Spring Boot application, 
 * we can't use @GeneratedValue annotation in our models as it's not available. 
 * Hence we need a method to produce the same effect as we'll have if we're 
 * using JPA and an SQL database.
 * 
 * The general solution to this problem is simple. We'll create a collection 
 * (table) that'll store the generated sequence for other collections. 
 * During the creation of a new record, we'll use it to fetch the next value.
 * 
 * */


package com.lele.empcrud.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/*
 * Create a collection that'll store the auto-incremented sequence for other collections. 
 * We'll call this collection database_sequences. It can be created using either the 
 * mongo shell or MongoDB Compass. 
 * 
 * Create a corresponding model class DatabaseSequence:
 * 
 * */
// Persist collection as document objects to MongoDB (equal to @Entity in JPA)
@Document(collection = "database_sequences")
public class DatabaseSequence {
	
	@Id // object's id
	private String id;
	private long seq;
	
	// constructor
	public DatabaseSequence() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}
}
