package com.zealroom.room.booking.system.repositories;

import com.zealroom.room.booking.system.entities.Booking;
import com.zealroom.room.booking.system.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking, String> {
    @Query("SELECT r FROM Room r JOIN Booking b WHERE b.checkIn >= :checkIn AND b.checkOut <= :checkOut")
    List<Room> findByCheckInAndCheckOut(LocalDateTime checkIn, LocalDateTime checkOut);

    @Query("SELECT b FROM Booking b WHERE b.room = :room AND ((b.checkIn < :room AND b.checkOut > :room) OR (b.checkIn < :checkIn  AND b.checkOut > :checkOut))")
    List<Booking> findAllByRoomAndCheckInCheckOut(Room room, LocalDateTime checkIn, LocalDateTime checkOut);

    @Query("SELECT b FROM Booking b WHERE b.userUuid.id = :id")
    List<Booking> findAllByUserId(String id);

    @Query("SELECT b FROM Booking b WHERE b.id = :id")
    Optional<Booking> findById(String id);

    @Query("SELECT b FROM Booking b WHERE b.id = :id")
    Booking findBookingById(String id);
}