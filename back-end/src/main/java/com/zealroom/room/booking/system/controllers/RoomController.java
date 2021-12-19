package com.zealroom.room.booking.system.controllers;
import com.zealroom.room.booking.system.entities.User;
import com.zealroom.room.booking.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.zealroom.room.booking.system.entities.Room;
import com.zealroom.room.booking.system.entities.Booking;
import com.zealroom.room.booking.system.repositories.RoomRepository;
import com.zealroom.room.booking.system.repositories.BookingRepository;

import javax.sound.sampled.FloatControl;
import java.time.LocalDate;
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

    @GetMapping("/date/{checkIn}{checkOut}")
    private List<Room> date(@PathVariable LocalDate checkIn, @PathVariable LocalDate checkOut) {
        return bookingRepository.findByCheckInAndCheckOut(checkIn, checkOut);
    }

    @GetMapping("/capacity/{cap}")
    private List<Room> capacity(@PathVariable int cap) {
        return roomRepository.findByCapacityGreaterThanEqual(cap);
    }

    @GetMapping("/Number/{roomNum}")
    private List<Room> roomNumber(@PathVariable String roomNum) {
        return roomRepository.findByRoomNumber(roomNum);
    }

    private List<Room> FindRoomByDescription(List<String> desc){
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
