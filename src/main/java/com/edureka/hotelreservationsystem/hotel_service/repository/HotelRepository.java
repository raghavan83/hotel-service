package com.edureka.hotelreservationsystem.hotel_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edureka.hotelreservationsystem.hotel_service.entity.HotelRoom;

public interface HotelRepository extends JpaRepository<HotelRoom, Long>{

}
