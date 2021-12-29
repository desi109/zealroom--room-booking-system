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

    @NotNull
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy="organization",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserOrganizationConnection> users;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<UserOrganizationConnection> getUsers(){
        return users;
    }
}
