package com.zealroom.room.booking.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zealroom.room.booking.system.entities.Room;
import com.zealroom.room.booking.system.entities.Booking;
import com.zealroom.room.booking.system.repositories.RoomRepository;
import com.zealroom.room.booking.system.repositories.BookingRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "localhost:3001")
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping
    private Room add(@RequestBody Room room){
        return roomRepository.save(room);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable Integer id){
        roomRepository.deleteById(id.toString());
    }

    @PostMapping("/book/room")
    private void bookExactRoom(@RequestBody Booking booking) {
        //Check if there is any booking record for some room and time period. If this list is empty this room is available for that period
        //if it is not empty it is already booked
        List<Booking> bookings = bookingRepository.findAllByRoomAndCheckInCheckOut(booking.getRoom(),
                booking.getCheckIn(), booking.getCheckOut());
        
        if (bookings!=null) {
            bookingRepository.save(booking);
        } else {
            throw new IllegalArgumentException("This room is already booked for this time period");
        }
    }

    @GetMapping("/date/{checkIn}/{checkOut}")
    private List<Room> date(@PathVariable LocalDateTime checkIn, @PathVariable LocalDateTime checkOut) {
        return bookingRepository.findByCheckInAndCheckOut(checkIn, checkOut);
    }

    @GetMapping("/capacity/{cap}")
    private List<Room> capacity(@PathVariable int cap) {
        return roomRepository.findByCapacityGreaterThanEqual(cap);
    }

    @GetMapping("/number/{roomNum}")
    private List<Room> roomNumber(@PathVariable String roomNum) {
        return roomRepository.findByRoomNumber(roomNum);
    }

    @GetMapping("/description/{desc}")
    private List<Room> FindRoomByDescription(@PathVariable List<String> desc){
        List<Room> rooms = roomRepository.findByRoomDescriptionIsContaining(desc.get(0));
        List<Room> filter = new ArrayList<>();

        for (String n: desc
        ) {
            List<Room> result = roomRepository.findByRoomDescriptionIsContaining(n);

            for (Room res: result
            ) {
                if (rooms.contains(res)){
                    filter.add(res);
                }
            }
            rooms = filter;
        }

        return rooms;
    }
}
