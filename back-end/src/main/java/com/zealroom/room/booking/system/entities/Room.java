package com.zealroom.room.booking.system.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "rooms")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Room {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    @Column(name = "room_uuid")
    private String id;

    @Column(name = "capacity")
    @NotNull
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "organization_uuid", referencedColumnName = "organization_uuid")
    private Organization organization;

    @Column(name = "room_name")
    @NotNull
    private String roomName;

    @Column(name = "equipments")
    private String equipments;


    public String getEquipments() {
        return equipments;
    }

    public void setEquipments(String equipments) {
        this.equipments = equipments;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}