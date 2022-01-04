package com.zealroom.room.booking.system.controllers;

import com.zealroom.room.booking.system.entities.Booking;
import com.zealroom.room.booking.system.repositories.BookingRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "localhost:4200")
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/all")
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Optional<Booking> result = bookingRepository.findById(id);
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.ok("No booking found!");
    }


    @GetMapping("/user/{id}")
    public List<Booking> getByUserId(@PathVariable String id) {
        return bookingRepository.findAllByUserId(id);
    }
}
