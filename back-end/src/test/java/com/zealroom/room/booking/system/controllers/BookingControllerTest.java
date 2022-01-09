package com.zealroom.room.booking.system.controllers;

import com.zealroom.room.booking.system.entities.Booking;
import com.zealroom.room.booking.system.entities.Room;
import com.zealroom.room.booking.system.entities.User;
import com.zealroom.room.booking.system.repositories.BookingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookingControllerTest {

    @Mock
    private BookingRepository bookingRepository;
    @InjectMocks
    private BookingController bookingController;

    @Test
    void getAll_shouldReturnListOfBookings() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");

        Room room = new Room();
        room.setCapacity(100);
        room.setId("1");
//        room.setRoomDescription("testRoomDesc");
//        room.setRoomNumber("testRoomDescest1");

        Booking booking1 = new Booking();
        booking1.setId("1");
        booking1.setIsBooked(true);
        booking1.setUserUuid(user);
        LocalDateTime checkIn = LocalDateTime.of(2021,
                Month.DECEMBER, 29, 19, 30, 40);
        LocalDateTime checkOut = LocalDateTime.of(2021,
                Month.DECEMBER, 29, 20, 30, 40);
        booking1.setCheckIn(checkIn);
        booking1.setCheckOut(checkOut);
        booking1.setRoom(room);

        Booking booking2 = new Booking();
        booking2.setId("2");
        booking2.setIsBooked(true);
        booking2.setUserUuid(user);
        LocalDateTime checkIn2 = LocalDateTime.of(2021,
                Month.DECEMBER, 28, 19, 30, 40);
        LocalDateTime checkOut2 = LocalDateTime.of(2021,
                Month.DECEMBER, 28, 20, 30, 40);
        booking2.setCheckIn(checkIn2);
        booking2.setCheckOut(checkOut2);
        booking2.setRoom(room);

        Booking booking3 = new Booking();
        booking3.setId("3");
        booking3.setIsBooked(true);
        booking3.setUserUuid(user);
        LocalDateTime checkIn3 = LocalDateTime.of(2021,
                Month.DECEMBER, 30, 19, 30, 40);
        LocalDateTime checkOut3 = LocalDateTime.of(2021,
                Month.DECEMBER, 30, 20, 30, 40);
        booking3.setCheckIn(checkIn3);
        booking3.setCheckOut(checkOut3);
        booking3.setRoom(room);

        List<Booking> expected = new ArrayList<>();
        expected.add(booking1);
        expected.add(booking2);
        expected.add(booking3);

        when(bookingRepository.findAll()).thenReturn(expected);

        List<Booking> actual = bookingController.getAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getById_shouldReturnBookingWithSomeId() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");

        Room room = new Room();
        room.setCapacity(100);
        room.setId("1");
//        room.setRoomDescription("testRoomDesc");
//        room.setRoomNumber("test1");

        Booking booking1 = new Booking();
        booking1.setId("1");
        booking1.setIsBooked(true);
        booking1.setUserUuid(user);
        LocalDateTime checkIn = LocalDateTime.of(2021,
                Month.DECEMBER, 29, 19, 30, 40);
        LocalDateTime checkOut = LocalDateTime.of(2021,
                Month.DECEMBER, 29, 20, 30, 40);
        booking1.setCheckIn(checkIn);
        booking1.setCheckOut(checkOut);
        booking1.setRoom(room);

        when(bookingRepository.findById("1")).thenReturn(Optional.of(booking1));
        ResponseEntity<?> actual = bookingController.getById("1");
        Assertions.assertEquals(new ResponseEntity(booking1, HttpStatus.OK), actual);
    }

    @Test
    void getByUserId_shouldReturnListOfBookings() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");

        Room room = new Room();
        room.setCapacity(100);
        room.setId("1");
//        room.setRoomDescription("testRoomDesc");
//        room.setRoomNumber("test1");

        Booking booking1 = new Booking();
        booking1.setId("1");
        booking1.setIsBooked(true);
        booking1.setUserUuid(user);
        LocalDateTime checkIn = LocalDateTime.of(2021,
                Month.DECEMBER, 29, 19, 30, 40);
        LocalDateTime checkOut = LocalDateTime.of(2021,
                Month.DECEMBER, 29, 20, 30, 40);
        booking1.setCheckIn(checkIn);
        booking1.setCheckOut(checkOut);
        booking1.setRoom(room);

        Booking booking2 = new Booking();
        booking2.setId("2");
        booking2.setIsBooked(true);
        booking2.setUserUuid(user);
        LocalDateTime checkIn2 = LocalDateTime.of(2021,
                Month.DECEMBER, 28, 19, 30, 40);
        LocalDateTime checkOut2 = LocalDateTime.of(2021,
                Month.DECEMBER, 28, 20, 30, 40);
        booking2.setCheckIn(checkIn2);
        booking2.setCheckOut(checkOut2);
        booking2.setRoom(room);

        Booking booking3 = new Booking();
        booking3.setId("3");
        booking3.setIsBooked(true);
        booking3.setUserUuid(user);
        LocalDateTime checkIn3 = LocalDateTime.of(2021,
                Month.DECEMBER, 30, 19, 30, 40);
        LocalDateTime checkOut3 = LocalDateTime.of(2021,
                Month.DECEMBER, 30, 20, 30, 40);
        booking3.setCheckIn(checkIn3);
        booking3.setCheckOut(checkOut3);
        booking3.setRoom(room);

        List<Booking> expected = new ArrayList<>();
        expected.add(booking1);
        expected.add(booking2);
        expected.add(booking3);

        when(bookingRepository.findAllByUserId(user.getId())).thenReturn(expected);

        List<Booking> actual = bookingController.getByUserId(user.getId());
        Assertions.assertEquals(expected, actual);
    }
}