package com.zealroom.room.booking.system.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Equipment {
    @Id
    @Column(name = "equipment_id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "equipment_name")
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
