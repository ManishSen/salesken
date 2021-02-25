package com.java.salesken.accomodationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidItemException extends Exception{
	
private static final long serialVersionUID=1L;
	
	public InvalidItemException(String message){
		super(message);
	}

}
