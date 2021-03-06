package com.zealroom.room.booking.system.entities;

import javax.persistence.*;

@Entity
@Table(name = "userorganizationconnections")
public class UserOrganizationConnection {
    @Id
    @GeneratedValue
    @Column(unique = true,name = "connection_id")
    private long id;

    @Column(name = "is_manager")
    private boolean isManager;

    @Column(name = "position")
    private String position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_uuid")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_uuid")
    private Organization organization;

    public UserOrganizationConnection() {

    }

    public UserOrganizationConnection(Organization organization, User user, boolean isManager, String position) {
        this.user = user;
        this.organization = organization;
        this.isManager = isManager;
        this.position = position;
    }
    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }

    public long getId() {
        return id;
    }

}
