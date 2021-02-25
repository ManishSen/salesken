package com.java.salesken.accomodationservice.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java.salesken.accomodationservice.controller.ItemController;
import com.java.salesken.accomodationservice.entity.Item;
import com.java.salesken.accomodationservice.entity.ItemCategory;
import com.java.salesken.accomodationservice.entity.ReputationBadge;

public class ValidationUtils {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationUtils.class);
	
	public static List<String> isValidItem(Item item){
		LOGGER.info("Validating fields in Item::::::" );
		List<String> errorList = new ArrayList<>();
		String name = item.getName().toLowerCase();
		if(name.contains("free") || name.contains("offer") || name.contains("book") 
				|| name.contains("website")){
			LOGGER.info("Invalid name:: " + item.getName());
			errorList.add("Invalid name:: " + item.getName() + ", This words are not allowed : Free, Offer, Book, Website");
		}
		
		if(!(item.getRating()>=0 && item.getRating()<=5)){
			LOGGER.info("Invalid rating :: " + item.getRating());
			errorList.add("Invalid rating :: "+ item.getRating() +", Rating must be between 0 and 5");
		} 
		
		ItemCategory category = ItemCategory.fromValue(item.getCategory().toString().toUpperCase());
		
		if(category==null){
			LOGGER.info("Invalid category :: " + item.getCategory());
			errorList.add("Invalid category :: " + item.getCategory() +", Allowed categories are :: [hotel, alternative, hostel, lodge, resort, guesthouse]");
		}
		
		int zip = item.getLocation().getZip_code();
		if(zip<=9999 || zip>=99999){
			LOGGER.info("Invalid zipcode :: " + zip);
			errorList.add("Invalid zipcode :: " + zip + ", Valid zipcode length is : 5");
		}
		
		//validate URL
		int reputation = item.getReputation();
		if(!(reputation>=0 && reputation<=1000)){
			LOGGER.info("Invalid reputation :: " + reputation);
			errorList.add("Invalid reputation :: " + reputation + ", Valid reputation is from 0 to 1000.");
		}
		
		int price = item.getPrice();
		if(price<0){
			LOGGER.info("Invalid price :: " + price);
			errorList.add("Invalid price :: " + price + ", Price can not be negative.");
		}
		
		int availability = item.getAvailability();
		if(availability<0){
			LOGGER.info("Invalid availability :: " + availability);
			errorList.add("Invalid availability :: " + availability + ", Availability can not be negative.");
		}
		
		return errorList;
	}
}
