package com.zealroom.room.booking.system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zealroom.room.booking.system.entities.User;

import java.util.List;

//TODO add possible queries for password changes
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.lastName = ?1")
    List<User> findByLastName(String lastName);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findByUuid(String uuid);

    @Query("SELECT u FROM User u WHERE u.firstName = ?1")
    List<User> findByFirstName(String firstName);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.sessionToken = ?1")
    User findBySessionToken(String sessionToken);
}
