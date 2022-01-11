package com.zealroom.room.booking.system.controllers;

import com.zealroom.room.booking.system.entities.*;
import com.zealroom.room.booking.system.repositories.*;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "localhost:4200")
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserOrganizationConnectionRepository uoc;

    @PostMapping("/add/{uuid}")
    public ResponseEntity add(@PathVariable String uuid, @RequestBody Room room){
        Organization organization = organizationRepository.findByUuid(uuid);
        if (organization == null) {
            return new ResponseEntity<>("Incorrect invite token",HttpStatus.BAD_REQUEST);
        }
        room.setOrganization(organization);
        roomRepository.save(room);
        return  new ResponseEntity<>("Room added successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        roomRepository.deleteById(id.toString());
    }

    @PostMapping("/book/room")
    public void bookExactRoom(@RequestBody Booking booking) {
        //Check if there is any booking record for some room and time period. If this list is empty this room is available for that period
        //if it is not empty it is already booked
        List<Booking> bookings = bookingRepository.findAllByRoomAndCheckInCheckOut(booking.getRoom(),
                booking.getCheckIn(), booking.getCheckOut());
        
        if (bookings == null) {
            bookingRepository.save(booking);
        } else {
            throw new IllegalArgumentException("This room is already booked for this time period");
        }
    }

    @GetMapping("/{id}")
    public Room findById(@PathVariable String id) {
        return roomRepository.getById(id);
    }

    @GetMapping("/date/{checkIn}/{checkOut}")
    public List<Room> date(@PathVariable LocalDateTime checkIn, @PathVariable LocalDateTime checkOut) {
        return bookingRepository.findByCheckInAndCheckOut(checkIn, checkOut);
    }

    @GetMapping("/capacity/{cap}")
    public List<Room> capacity(@PathVariable int cap) {
        return roomRepository.findByCapacityGreaterThanEqual(cap);
    }

    @GetMapping("/get/rooms")
    public ResponseEntity getUserBookings(@RequestHeader("session-token")  String sessionToken){
        User user = userRepository.findBySessionToken(sessionToken);
        if(user == null){
            return new ResponseEntity<>("Incorrect sessionToken.", HttpStatus.BAD_REQUEST);
        }
        List<Room> rooms = roomRepository.findAll();

        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/get/organizationRooms/{uuid}")
    public ResponseEntity getRooms(@PathVariable String uuid, @RequestHeader("session-token")  String sessionToken){
        User user = userRepository.findBySessionToken(sessionToken);
        if(user == null){
            return new ResponseEntity<>("Incorrect sessionToken.", HttpStatus.BAD_REQUEST);
        }
        List<Room> rooms = roomRepository.findRoomsByOrganizationId(uuid);

        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}
