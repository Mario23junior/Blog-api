package com.api.project.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String sourceName;
	private String filedName;
	private Long filedValue;

	public ResourceNotFoundException(String sourceName, String filedName, Long filedValue) {
		super(String.format("%s not found with %s : '%s'", sourceName, filedValue, filedValue));
		this.sourceName = sourceName;
		this.filedName = filedName;
		this.filedValue = filedValue;
	}

	public String getSourceName() {
		return sourceName;
	}

	public String getFiledName() {
		return filedName;
	}

	public Long getFiledValue() {
		return filedValue;
	}

}
