package com.zealroom.room.booking.system.repositories;

import com.zealroom.room.booking.system.entities.Organization;
import com.zealroom.room.booking.system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OrganizationRepository extends JpaRepository<Organization, String> {
    @Query("SELECT o FROM Organization o WHERE o.id = ?1")
    Organization findByUuid(String uuid);

    @Query("SELECT o FROM Organization o WHERE o.inviteToken = ?1 OR o.moderatorInviteToken = ?1")
    Organization findByInviteToken(String inviteToken);
}
