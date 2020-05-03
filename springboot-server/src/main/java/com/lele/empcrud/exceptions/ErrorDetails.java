/*
 * Default error response provided by Spring Boot contains all the details 
 * that are typically needed. However, you might want to create a framework 
 * independent response structure for your organization. In that case, you 
 * can define a specific error response structure. 
 * 
 * Let’s define a simple error response bean.
 * 
 * */

package com.lele.empcrud.exceptions;

import java.util.Date;

public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;

	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
