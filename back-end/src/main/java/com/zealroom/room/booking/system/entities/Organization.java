package com.zealroom.room.booking.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="organizations")
public class Organization {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    @Column(name = "organization_uuid")
    private String id;

    @OneToMany(mappedBy="organization",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserOrganizationConnection> users;

    public List<UserOrganizationConnection> getUsers(){
        return users;
    }
}
