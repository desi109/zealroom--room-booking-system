package com.zealroom.room.booking.system.controllers;

import com.zealroom.room.booking.system.entities.Equipment;
import com.zealroom.room.booking.system.entities.Room;
import com.zealroom.room.booking.system.entities.User;
import com.zealroom.room.booking.system.repositories.EquipmentRepository;
import com.zealroom.room.booking.system.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipmentControllerTest {

    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private EquipmentController equipmentController;

    @Test
    public void add() {
        Equipment equipment = new Equipment();
        equipment.setId(1);

        Room room = new Room();
        room.setCapacity(100);
        room.setId("1");
        equipment.setRoom(room);
        equipment.setName("TV");

        when(equipmentRepository.save(equipment)).thenReturn(equipment);

        Equipment saved = equipmentController.add(equipment);

        Assertions.assertEquals(equipment, saved);
    }

    @Test
    public void getAll() {
        Equipment equipment1 = new Equipment();
        equipment1.setId(1);
        equipment1.setName("TV");

        Equipment equipment2 = new Equipment();
        equipment2.setId(2);
        equipment2.setName("Desk");

        Equipment equipment3 = new Equipment();
        equipment3.setId(3);
        equipment3.setName("Computer");

        Room room = new Room();
        room.setCapacity(100);
        room.setId("1");
        equipment1.setRoom(room);
        equipment2.setRoom(room);
        equipment3.setRoom(room);

        List<Equipment> expected = new ArrayList<>();
        expected.add(equipment1);
        expected.add(equipment2);
        expected.add(equipment3);

        when(equipmentRepository.findAll()).thenReturn(expected);

        List<Equipment> actual = equipmentController.getAll();
        Assertions.assertEquals(expected, actual);
    }

}
