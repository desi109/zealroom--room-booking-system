package com.zealroom.room.booking.system.controllers;

import com.zealroom.room.booking.system.entities.Booking;
import com.zealroom.room.booking.system.entities.User;
import com.zealroom.room.booking.system.repositories.BookingRepository;
import com.zealroom.room.booking.system.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "localhost:4200")
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Optional<Booking> result = bookingRepository.findById(id);
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.ok("No booking found!");
    }

    @DeleteMapping("/delete/{bookingUuid}")
    public ResponseEntity deleteBooking(@PathVariable String bookingUuid, @RequestHeader("session-token") String sessionToken){
        User user = userRepository.findBySessionToken(sessionToken);
        if(user == null){
            return new ResponseEntity<>("Incorrect sessionToken",HttpStatus.BAD_REQUEST);
        }

        Booking booking = bookingRepository.findBookingById(bookingUuid);
        if(booking == null){
            return new ResponseEntity<>("Incorrect uuid",HttpStatus.BAD_REQUEST);
        }
        bookingRepository.delete(booking);
        return new ResponseEntity<>("Booking deleted",HttpStatus.OK);

    }

    @GetMapping("/user/{id}")
    public List<Booking> getByUserId(@PathVariable String id) {
        return bookingRepository.findAllByUserId(id);
    }
}
