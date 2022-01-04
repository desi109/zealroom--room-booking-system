package com.zealroom.room.booking.system.controllers;

import com.zealroom.room.booking.system.entities.Booking;
import com.zealroom.room.booking.system.entities.Room;
import com.zealroom.room.booking.system.entities.User;
import com.zealroom.room.booking.system.repositories.BookingRepository;
import com.zealroom.room.booking.system.repositories.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomControllerTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private RoomController roomController;

    @Test
    void save_shouldReturnSavedRoom() {
        Room expected = new Room();
        expected.setCapacity(100);
        expected.setId("1");
        expected.setRoomDescription("testRoomDesc");
        expected.setRoomNumber("test1");

        when(roomRepository.save(any())).thenReturn(expected);

        Room actual = roomController.add(expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void delete_shouldGoIntoDeleteMethodWIthGivenId() {
        Room expected = new Room();
        expected.setCapacity(100);
        expected.setId("1");
        expected.setRoomDescription("testRoomDesc");
        expected.setRoomNumber("test1");

        when(roomRepository.save(any())).thenReturn(expected);

        roomController.delete(1);
        Mockito.verify(roomRepository, times(1)).deleteById("1");
    }

    @Test
    void bookExactRoom_shouldGoIntoSaveMethod() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");

        Room room = new Room();
        room.setCapacity(100);
        room.setId("1");
        room.setRoomDescription("testRoomDesc");
        room.setRoomNumber("test1");

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

        when(bookingRepository.findAllByRoomAndCheckInCheckOut(any(), any(), any())).thenReturn(null);
        when(bookingRepository.save(any())).thenReturn(booking1);

        roomController.bookExactRoom(booking1);
        Mockito.verify(bookingRepository, times(1)).save(booking1);
    }

    @Test
    void bookExactRoom_shouldThrowException() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");

        Room room = new Room();
        room.setCapacity(100);
        room.setId("1");
        room.setRoomDescription("testRoomDesc");
        room.setRoomNumber("test1");

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

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        when(bookingRepository.findAllByRoomAndCheckInCheckOut(any(), any(), any())).thenReturn(bookings);
        when(bookingRepository.save(any())).thenReturn(booking1);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            roomController.bookExactRoom(booking1);
        });

        Assertions.assertEquals("This room is already booked for this time period", thrown.getMessage());
    }

    @Test
    void date_shouldReturnListOfRooms() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");

        Room room = new Room();
        room.setCapacity(100);
        room.setId("1");
        room.setRoomDescription("testRoomDesc");
        room.setRoomNumber("test1");

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

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        when(bookingRepository.findByCheckInAndCheckOut(checkIn, checkOut)).thenReturn(rooms);

        Assertions.assertEquals(roomController.date(checkIn, checkOut), rooms);
    }

    @Test
    void capacity_shouldReturnListOfRooms() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");

        Room room = new Room();
        room.setCapacity(100);
        room.setId("1");
        room.setRoomDescription("testRoomDesc");
        room.setRoomNumber("test1");

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

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        when(roomRepository.findByCapacityGreaterThanEqual(99)).thenReturn(rooms);

        Assertions.assertEquals(roomController.capacity(99), rooms);
    }

    @Test
    void roomNumber_shouldReturnListOfRooms() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");

        Room room = new Room();
        room.setCapacity(100);
        room.setId("1");
        room.setRoomDescription("testRoomDesc");
        room.setRoomNumber("test1");

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

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        when(roomRepository.findByRoomNumber("test1")).thenReturn(rooms);

        Assertions.assertEquals(roomController.roomNumber("test1"), rooms);
    }

    @Test
    void FindRoomByDescription_shouldReturnListOfRooms() {
        String desc1 = "testRoomDesc";
        String desc2 = "testDesc2";
        String desc3 = "testDesc3";

        List<String> desc = new ArrayList<>();
        desc.add(desc1);
        desc.add(desc2);
        desc.add(desc3);

        Room room = new Room();
        room.setCapacity(100);
        room.setId("1");
        room.setRoomDescription("testRoomDesc");
        room.setRoomNumber("test1");
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        when(roomRepository.findByRoomDescriptionIsContaining(any())).thenReturn(rooms);

        Assertions.assertEquals(new HashSet<>(rooms), roomController.FindRoomByDescription(desc));
    }
}