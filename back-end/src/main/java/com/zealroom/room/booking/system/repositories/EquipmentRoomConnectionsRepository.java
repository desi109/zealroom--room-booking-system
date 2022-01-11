package com.zealroom.room.booking.system.repositories;

import com.zealroom.room.booking.system.entities.EquipmentRoomConnection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EquipmentRoomConnectionsRepository extends JpaRepository<EquipmentRoomConnection, String> {
    @Query("SELECT e.id FROM EquipmentRoomConnection e WHERE e.room = :roomId")
    List<Long> findEquipmentIdByRoomId(String roomId);
}