package com.zealroom.room.booking.system.repositories;

import com.zealroom.room.booking.system.entities.Organization;
import com.zealroom.room.booking.system.entities.User;
import com.zealroom.room.booking.system.entities.UserOrganizationConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserOrganizationConnectionRepository extends JpaRepository<UserOrganizationConnection, Long> {
    @Query("SELECT uoc.user FROM UserOrganizationConnection uoc WHERE uoc.organization = ?1 AND uoc.isManager = true")
    List<User> getOrganizationModerators(Organization organization);
}
