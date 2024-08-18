package com.edureka.hotelreservationsystem.hotel_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edureka.hotelreservationsystem.hotel_service.entity.HotelRoom;
import com.edureka.hotelreservationsystem.hotel_service.service.HotelService;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@PostMapping
	public ResponseEntity<HotelRoom> addHotelRoom(@RequestBody HotelRoom hotelRoom) {
		return ResponseEntity.ok(this.hotelService.createHotelRoom(hotelRoom));
	}

	@PostMapping("/check-availability")
	public ResponseEntity<Boolean> checkAvailability(@RequestParam Long roomId) {
		boolean isAvailable = hotelService.checkAvailability(roomId);
		return ResponseEntity.ok(isAvailable);
	}

	@PostMapping("/reserve")
	public ResponseEntity<Boolean> reserveRoom(@RequestParam Long roomId) {
		hotelService.reserveHotelRoom(roomId);
		return ResponseEntity.ok(true);
	}

	// Retrieve all hotelRooms
	@GetMapping
	public ResponseEntity<List<HotelRoom>> getAllHotelRooms() {
		List<HotelRoom> hotelRooms = hotelService.getAllHotelRooms();
		return new ResponseEntity<>(hotelRooms, HttpStatus.OK);
	}

	// Get a HotelRoom by ID
	@GetMapping("/{id}")
	public ResponseEntity<HotelRoom> getHotelRoomById(@PathVariable Long id) {
		HotelRoom hotelRoom = hotelService.getHotelRoomById(id);
		if (hotelRoom != null) {
			return new ResponseEntity<>(hotelRoom, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Update a HotelRoom
	@PutMapping("/{id}")
	public ResponseEntity<HotelRoom> updateHotelRoom(@PathVariable Long id, @RequestBody HotelRoom hotelRoom) {
		HotelRoom updatedHotelRoom = hotelService.updateHotelRoom(id, hotelRoom);
		if (updatedHotelRoom != null) {
			return new ResponseEntity<>(updatedHotelRoom, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Delete a HotelRoom
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteHotelRoom(@PathVariable Long id) {
		boolean deleted = hotelService.deleteHotelRoom(id);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
