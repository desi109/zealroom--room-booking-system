package com.zealroom.room.booking.system.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    @Column(name = "room_uuid")
    private String id;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "room_description", nullable = false)
    private String roomDescription;

    @ManyToOne
    @JoinColumn(name="organization_uuid", nullable=false)
    private Organization organization;

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