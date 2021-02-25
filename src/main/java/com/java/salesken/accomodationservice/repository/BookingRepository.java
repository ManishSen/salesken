package com.java.salesken.accomodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.salesken.accomodationservice.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}
