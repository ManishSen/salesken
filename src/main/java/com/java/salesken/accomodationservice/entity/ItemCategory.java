package com.java.salesken.accomodationservice.entity;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum ItemCategory {
	
	@JsonProperty("hotel")
	HOTEL("hotel"),
	
	@JsonProperty("hostel")
	HOSTEL("hostel"),
	
	@JsonProperty("lodge")
	LODGE("lodge"),
	
	@JsonProperty("alternative")
	ALTERNATIVE("alternative"),
	
	@JsonProperty("resort")
	RESORT("resort"),
	
	@JsonProperty("guesthouse")
	GUESTHOUSE("guesthouse");
	
	private String value;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemCategory.class); 
	
	private ItemCategory(String value){
		this.value= value.toLowerCase();
	}
	
	@JsonCreator
	public static ItemCategory fromValue(String value){
		LOGGER.info("inside from value ::: " + value);
		for(ItemCategory item :values()){
			if(item.value.equalsIgnoreCase(value)){
				return item;
			}
		}
		return null;
	}
	
	

}
