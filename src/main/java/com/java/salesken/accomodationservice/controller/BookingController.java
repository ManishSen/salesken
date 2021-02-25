package com.java.salesken.accomodationservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.salesken.accomodationservice.entity.Booking;
import com.java.salesken.accomodationservice.entity.Item;
import com.java.salesken.accomodationservice.exception.BookingNotFoundException;
import com.java.salesken.accomodationservice.exception.InvalidItemException;
import com.java.salesken.accomodationservice.exception.ItemNotFoundException;
import com.java.salesken.accomodationservice.exception.RoomsUnavailableException;
import com.java.salesken.accomodationservice.service.BookingService;

@RestController
@RequestMapping("/api/v1/")
public class BookingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private BookingService bookingService;
	
	
	@GetMapping("bookings")
	public List<Booking> getAllBooking(){
		LOGGER.info("get All Bookings" );
		return bookingService.getAllBooking();
	}
	
	@GetMapping("booking/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable(value="id") Long id) throws BookingNotFoundException {
			LOGGER.info("Searching with booking id :" + id);
			Booking booking = bookingService.getBooking(id);
			return ResponseEntity.ok().body(booking);
	}
	
	@PostMapping("booking")
	public Booking addBooking(@RequestBody Booking booking) throws InvalidItemException, RoomsUnavailableException{
		LOGGER.info("Adding Booking  :" );
		return bookingService.addBooking(booking);
	}
	
	@PostMapping("booking/{id}")
	public Booking updateBooking(@PathVariable(value="id") Long id) throws InvalidItemException, RoomsUnavailableException, BookingNotFoundException, ItemNotFoundException{
		LOGGER.info("vacating rooms from Booking  :" + id);
		return bookingService.vacateBooking(id);
	}
	
	
}
