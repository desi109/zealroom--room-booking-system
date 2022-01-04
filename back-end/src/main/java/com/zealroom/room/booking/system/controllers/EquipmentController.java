package com.zealroom.room.booking.system.controllers;

import com.zealroom.room.booking.system.entities.Equipment;
import com.zealroom.room.booking.system.entities.Room;
import com.zealroom.room.booking.system.entities.User;
import com.zealroom.room.booking.system.repositories.EquipmentRepository;
import com.zealroom.room.booking.system.repositories.EquipmentRoomConnectionsRepository;
import com.zealroom.room.booking.system.repositories.OrganizationRepository;
import com.zealroom.room.booking.system.repositories.RoomRepository;
import com.zealroom.room.booking.system.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EquipmentRoomConnectionsRepository equipmentRoomConnectionsRepository;

    @PostMapping
    public Equipment add(@RequestBody Equipment equipment){
        return equipmentRepository.save(equipment);
    }

    @GetMapping
    public List<Equipment> getAll(){
        return equipmentRepository.findAll();
    }

    @GetMapping("/get/{roomId}")
    public ResponseEntity getById(@PathVariable String roomId, @RequestHeader("session-token")  String sessionToken) {
        User user = userRepository.findBySessionToken(sessionToken);
        if(user == null){
            return new ResponseEntity<>("Incorrect sessionToken.", HttpStatus.BAD_REQUEST);
        }
        String roomEquipment = roomRepository.findById(roomId).toString();
        return new ResponseEntity<>(roomEquipment, HttpStatus.OK);
    }

    /*@GetMapping("/getByRoom/{roomId}")
    public String getAllEquipmentsByRoomId(@PathVariable String roomId) {
        /*List<String> roomEquipment = new ArrayList<>();
        List<Long> allEquipmentIdsByRoomId = equipmentRoomConnectionsRepository.findEquipmentIdByRoomId(roomId);
        Optional<String> result = null;

        for (Long equipmentId : allEquipmentIdsByRoomId) {
            roomEquipment.add(equipmentRepository.findEquipmentNameByEquipmentId(equipmentId));
        }
        result = Optional.ofNullable(roomEquipment.toString());*/

        /*List<Long> allEquipmentIdsByRoomId = equipmentRoomConnectionsRepository.findEquipmentIdByRoomId(roomId);
        List<String> result = new ArrayList<>();
        String equipmentName

        for (Long equipmentId : allEquipmentIdsByRoomId) {
            String equipmentName = equipmentRepository.findEquipmentNameByEquipmentId(equipmentId);
            if (equipment == ) {
                return new ResponseEntity<>("Incorrect uuid.", HttpStatus.BAD_REQUEST);
            }
        }

       // return result.toString();//.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.ok("No equipment found!");

        List<User> organizationUsers = uoc.getOrganizationUsers(uuid);
        if(!organizationUsers.contains(user)){
            return new ResponseEntity<>("Only users part of the organization can generate invite token.",HttpStatus.BAD_REQUEST);
        }
        String inviteToken = HelperService.generateNewToken();
        organization.setInviteToken(inviteToken);
        organizationRepository.save(organization);

        return new ResponseEntity<>(inviteToken,HttpStatus.OK);
    }*/
}
