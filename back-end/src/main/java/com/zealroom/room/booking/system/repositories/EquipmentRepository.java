package com.zealroom.room.booking.system.repositories;

import com.zealroom.room.booking.system.entities.Equipment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    @Query("SELECT e.name FROM Equipment e WHERE e.room.id = :roomId")
    List<String> findEquipmentForRoom(String roomId);
}