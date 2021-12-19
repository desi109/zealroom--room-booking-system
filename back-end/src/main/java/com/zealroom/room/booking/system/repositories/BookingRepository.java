package com.zealroom.room.booking.system.repositories;

import com.zealroom.room.booking.system.entities.Booking;
import com.zealroom.room.booking.system.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking, String> {
    @Query("select r from Room r JOIN Booking b where b.checkIn >= ?1 and b.checkOut <= ?1")
    List<Room> findByCheckInAndCheckOut(LocalDate checkIn, LocalDate checkOut);


}