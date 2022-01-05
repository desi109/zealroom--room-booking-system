package com.zealroom.room.booking.system.repositories;

import com.zealroom.room.booking.system.entities.Equipment;
import com.zealroom.room.booking.system.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

}