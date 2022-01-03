package com.zealroom.room.booking.system.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @Column(name = "room_uuid", nullable = false)
    private String id;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "room_description", nullable = false)
    private String roomDescription;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}