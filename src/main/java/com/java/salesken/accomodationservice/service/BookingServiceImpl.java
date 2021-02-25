package com.java.salesken.accomodationservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.salesken.accomodationservice.controller.BookingController;
import com.java.salesken.accomodationservice.entity.Booking;
import com.java.salesken.accomodationservice.entity.Item;
import com.java.salesken.accomodationservice.exception.BookingNotFoundException;
import com.java.salesken.accomodationservice.exception.InvalidItemException;
import com.java.salesken.accomodationservice.exception.ItemNotFoundException;
import com.java.salesken.accomodationservice.exception.RoomsUnavailableException;
import com.java.salesken.accomodationservice.repository.BookingRepository;
import com.java.salesken.accomodationservice.repository.ItemRepository;

@Service
public class BookingServiceImpl implements BookingService{

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Override
	public List<Booking> getAllBooking() {
		LOGGER.info("inside getAll booking :" );
		return bookingRepository.findAll();
	}
	
	@Override
	public Booking getBooking(Long id) throws BookingNotFoundException {
		LOGGER.info("inside get Booking by Id :" );
		Booking booking = bookingRepository.findById(id)
						.orElseThrow(() -> new BookingNotFoundException("Booking not found for this id:: "+ id));
		return booking;
	}

	@Override
	public Booking addBooking(Booking booking) throws InvalidItemException, RoomsUnavailableException {
		long itemId = booking.getItemId();
		Optional<Item> optItem = itemRepository.findById(itemId);
		LOGGER.info("Validating hotelId ::" );
		Item item =optItem.get();
		if(item==null){
			throw new InvalidItemException("Given Hotel Id is invalid::"+ itemId);
		}
		LOGGER.info("Doing booking for hotel ::" );
		int availability = item.getAvailability();
		if(availability>=booking.getRooms()){
			LOGGER.info("Rooms are available, Complete booking for hotel ::" + item.getName() );
				item.setAvailability(availability-booking.getRooms());
				itemRepository.save(item);
				bookingRepository.save(booking);
		}else{
			LOGGER.info("Rooms are unavailable.Please try another Item ::" );
			throw new RoomsUnavailableException("Rooms are unavailable for item Id ::" + item.getId());
		}
		
		return booking;
	}
	
	@Override
	public Booking vacateBooking(Long id) throws BookingNotFoundException, ItemNotFoundException {
		LOGGER.info("inside vacate booking :" );
		Booking booking = bookingRepository.findById(id)
				.orElseThrow(() -> new BookingNotFoundException("Booking not found for this id:: "+ id));
		long itemId = booking.getItemId();
		LOGGER.info("itemId :" + itemId );
		Optional<Item> item = itemRepository.findById(itemId);
		if(item.get()!=null){
			Item oneItem = item.get();
			int availability = oneItem.getAvailability();
			oneItem.setAvailability(availability+booking.getRooms());
			itemRepository.save(oneItem);
			booking.setStatus("COMPLETE");
			bookingRepository.save(booking);
		}else{
			throw new ItemNotFoundException("Unable to vacate rooms.Inavalid Item: "+ itemId);
		}
		
		return booking;
	}

}
