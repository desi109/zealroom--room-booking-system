package com.zealroom.room.booking.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="organizations")
public class Organization {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    @Column(name = "organization_uuid")
    private String id;



    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "organization")
    List<Room> rooms;

    @NotNull
    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy="organization",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserOrganizationConnection> users;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<UserOrganizationConnection> getUsers(){
        return users;
    }
}
