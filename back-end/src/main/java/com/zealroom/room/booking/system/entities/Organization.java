package com.zealroom.room.booking.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="organizations")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
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
    @Column(name = "type")
    private String type;

    @Column(name = "invite_token")
    private String inviteToken;

    @Column(name = "moderator_invite_token")
    private String moderatorInviteToken;

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

    public String getInviteToken() {
        return inviteToken;
    }

    public void setInviteToken(String inviteToken) {
        this.inviteToken = inviteToken;
    }

    public String getModeratorInviteToken() {
        return moderatorInviteToken;
    }

    public void setModeratorInviteToken(String managerInviteToken) {
        this.moderatorInviteToken = managerInviteToken;
    }

    public List<UserOrganizationConnection> getUsers(){
        return users;
    }
}
