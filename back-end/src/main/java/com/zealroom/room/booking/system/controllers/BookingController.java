package com.zealroom.room.booking.system.controllers;

import com.zealroom.room.booking.system.entities.Booking;
import com.zealroom.room.booking.system.entities.User;
import com.zealroom.room.booking.system.repositories.BookingRepository;
import com.zealroom.room.booking.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "localhost:3001")
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    public Booking getById(@PathVariable String id) {
        return bookingRepository.findById(id).orElse(new Booking());
    }

    @GetMapping("/user/{id}")
    public List<Booking> getByUserId(@PathVariable String id) {
        return bookingRepository.findAllByUserId(id);
    }
}
