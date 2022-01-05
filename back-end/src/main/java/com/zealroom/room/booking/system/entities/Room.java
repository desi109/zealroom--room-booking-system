package com.zealroom.room.booking.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(name = "room_description")
    @NotNull
    private String roomDescription;

    @ManyToOne
    @JoinColumn(name = "organization_uuid", referencedColumnName = "organization_uuid")
    private Organization organization;

    @Column(name = "room_name")
    @NotNull
    private String roomName;

    @OneToMany(mappedBy="room", fetch = FetchType.EAGER)
    private List<Equipment> equipment;

    public Room() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
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

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }
}
