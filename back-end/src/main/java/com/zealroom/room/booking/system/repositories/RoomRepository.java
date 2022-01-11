package com.zealroom.room.booking.system.repositories;

import com.zealroom.room.booking.system.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RoomRepository extends JpaRepository<Room, String> {

    @Query("select r from Room r where r.capacity >= ?1")
    List<Room> findByCapacityGreaterThanEqual(Integer capacity);

    @Query("select r from Room r  WHERE r.organization.id = :uuid")
    List<Room> findRoomsByOrganizationId(String uuid);
}