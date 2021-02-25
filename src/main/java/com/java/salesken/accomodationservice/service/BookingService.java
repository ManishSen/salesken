package com.java.salesken.accomodationservice.service;

import java.util.List;

import com.java.salesken.accomodationservice.entity.Booking;
import com.java.salesken.accomodationservice.exception.BookingNotFoundException;
import com.java.salesken.accomodationservice.exception.InvalidItemException;
import com.java.salesken.accomodationservice.exception.ItemNotFoundException;
import com.java.salesken.accomodationservice.exception.RoomsUnavailableException;

public interface BookingService {

	List<Booking> getAllBooking();

	Booking addBooking(Booking booking) throws InvalidItemException, RoomsUnavailableException;

	Booking vacateBooking(Long id) throws BookingNotFoundException, ItemNotFoundException;

	Booking getBooking(Long id) throws BookingNotFoundException;

}
