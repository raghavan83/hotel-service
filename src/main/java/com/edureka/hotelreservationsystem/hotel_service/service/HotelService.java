package com.edureka.hotelreservationsystem.hotel_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edureka.hotelreservationsystem.hotel_service.entity.HotelRoom;
import com.edureka.hotelreservationsystem.hotel_service.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	public boolean checkAvailability(Long id) {
        HotelRoom hotelRoom = hotelRepository.findById(id).orElse(null);
        return hotelRoom != null && hotelRoom.isAvailable();
    }
	
	public HotelRoom reserveHotelRoom(Long id) {
		HotelRoom hotelRoom = hotelRepository.findById(id).orElse(null);
        if (hotelRoom != null && hotelRoom.isAvailable()) {
        	hotelRoom.setAvailable(false);
        	hotelRepository.save(hotelRoom);
        }
        return hotelRoom;
    }

	public HotelRoom createHotelRoom(HotelRoom hotelRoom) {
		return hotelRepository.save(hotelRoom);
	}

	public HotelRoom getHotelRoom(Long id) {
		return hotelRepository.findById(id).orElse(null);
	}

	public List<HotelRoom> getAllHotelRooms() {
		return hotelRepository.findAll();
	}

	public HotelRoom getHotelRoomById(Long id) {
		return hotelRepository.findById(id).orElse(null);
	}

	public HotelRoom addHotelRoom(HotelRoom hotelRoom) {
		return hotelRepository.save(hotelRoom);

		// Add Logic to post to Kafka topic
		// event UserRegistered
	}

	public HotelRoom updateHotelRoom(Long id, HotelRoom hotelRoom) {
		if (hotelRepository.existsById(id)) {
			hotelRoom.setId(id); // Ensure the correct ID is set for update
			return hotelRepository.save(hotelRoom);
		} else {
			return null;
		}

		// Add Logic to post to Kafka topic
		// event UserUpdated
	}

	public boolean deleteHotelRoom(Long id) {
		if (hotelRepository.existsById(id)) {
			hotelRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}
