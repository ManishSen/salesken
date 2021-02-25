package com.java.salesken.accomodationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class RoomsUnavailableException extends Exception{
private static final long serialVersionUID=1L;
	
	public RoomsUnavailableException(String message){
		super(message);
	}
}
