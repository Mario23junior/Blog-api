package com.api.project.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	private String message;
	
	public BlogApiException() {
		//TODO Auto-generated constructor stub
	}
	
	public BlogApiException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public BlogApiException(String message, HttpStatus status, String message1) {
		this.status = status;
		this.message = message1;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
