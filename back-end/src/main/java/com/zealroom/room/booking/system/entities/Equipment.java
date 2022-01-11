package com.zealroom.room.booking.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Equipment {
    @Id
    @Column(name = "equipment_id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "equipment_name")
    private String name;

    @ManyToOne
    @JoinColumn(name="room_uuid")
    @JsonIgnore
    private Room room;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
