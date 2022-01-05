package com.zealroom.room.booking.system.controllers;

import com.zealroom.room.booking.system.entities.Equipment;
import com.zealroom.room.booking.system.entities.Room;
import com.zealroom.room.booking.system.repositories.EquipmentRepository;
import com.zealroom.room.booking.system.repositories.OrganizationRepository;
import com.zealroom.room.booking.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentRepository equipmentRepository;

    @PostMapping
    public Equipment add(@RequestBody Equipment equipment){
        return equipmentRepository.save(equipment);
    }

    @GetMapping
    public List<Equipment> getAll(){
        return equipmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Equipment getById(@PathVariable Integer id) {
        return equipmentRepository.getById(id);
    }
}
