package com.java.salesken.accomodationservice.utils;

public class StringUtility {
	
	private static final String validItem = "a) A hotel name cannot contain the words ['Free', 'Offer', 'Book', 'Website'] \\n" + 
	"and it should be longer than 10 characters. \\n" +
			 "b) The rating must be >= 0 and <= 5. \\n" +
			 "c) The category can be one of [hotel, alternative, hostel, lodge, resort, guesthouse] \\n" +
			 "d) The zipcode MUST must have a length of 5. \\n" +
			 "e) The image MUST be a valid URL \\n" +
			 "f) The reputation MUST be >= 0 and <= 1000. \\n";

	
	public static final String RATING = "rating";
	public static final String CITY = "city";
	public static final String CATEGORY = "category";
	public static final String REPUTATIONBADGE = "reputationBadge";
	public static final String AVAILABILITY = "availability";
	
	
	public static String getValidItem() {
		return validItem;
	}

}
