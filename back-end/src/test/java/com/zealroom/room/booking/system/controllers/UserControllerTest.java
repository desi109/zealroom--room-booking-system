package com.zealroom.room.booking.system.controllers;

import com.zealroom.room.booking.system.entities.User;
import com.zealroom.room.booking.system.repositories.OrganizationRepository;
import com.zealroom.room.booking.system.repositories.UserOrganizationConnectionRepository;
import com.zealroom.room.booking.system.repositories.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserOrganizationConnectionRepository userOrganizationConnectionRepository;

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private UserController userController;

    @Test
    public void register_shouldReturnResponseEntity() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");

        when(userRepository.findByEmail(any())).thenReturn(null);

        ResponseEntity responseEntity = userController.register(user);
        Mockito.verify(userRepository, times(1)).save(any());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(responseEntity.getBody(), "Register successful");
    }

    @Test
    public void register_shouldThrowBadRequestWhenNoFirstName() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setLastName("testLastName");
        user.setPassword("testPassword");

        when(userRepository.findByEmail(any())).thenReturn(null);

        ResponseEntity responseEntity = userController.register(user);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(responseEntity.getBody(), "First Name must not be empty.");
    }

    @Test
    public void register_shouldThrowBadRequestWhenNoLastName() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testFName");
        user.setPassword("testPassword");

        when(userRepository.findByEmail(any())).thenReturn(null);

        ResponseEntity responseEntity = userController.register(user);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(responseEntity.getBody(), "Last Name must not be empty.");
    }

    @Test
    public void register_shouldThrowBadRequestWhenNoEmail() {
        User user = new User();
        user.setUuid("test");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");

        when(userRepository.findByEmail(any())).thenReturn(null);

        ResponseEntity responseEntity = userController.register(user);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(responseEntity.getBody(), "Email must not be empty.");
    }

    @Test
    public void register_shouldThrowBadRequestWhenNoPassword() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");

        when(userRepository.findByEmail(any())).thenReturn(null);

        ResponseEntity responseEntity = userController.register(user);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(responseEntity.getBody(), "Password must not be empty.");
    }

    @Test
    public void register_shouldThrowBadRequestWhenEmailIsUsed() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");

        when(userRepository.findByEmail(any())).thenReturn(user);

        ResponseEntity responseEntity = userController.register(user);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(responseEntity.getBody(), "Email already in use");
    }

    @Test
    public void registerAdmin_shouldThrowExceptionWhenNoAdminUser() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");
        when(userRepository.findBySessionToken(any())).thenReturn(null);

        ResponseEntity responseEntity = userController.registerAdmin(user, "sessionToken");
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(responseEntity.getBody(), "Incorrect sessionToken");
    }

    @Test
    public void registerAdmin_shouldThrowExceptionWhenNoUserIsNotAdmin() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");
        user.setIsAdmin(false);
        when(userRepository.findBySessionToken(any())).thenReturn(user);

        ResponseEntity responseEntity = userController.registerAdmin(user, "sessionToken");
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(responseEntity.getBody(), "Only admins can create admin accounts");
    }

    @Test
    public void registerAdmin_shouldThrowExceptionWhenNoFirstNameProvided() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setLastName("testLastName");
        user.setPassword("testPassword");
        user.setIsAdmin(true);
        when(userRepository.findBySessionToken(any())).thenReturn(user);

        ResponseEntity responseEntity = userController.registerAdmin(user, "sessionToken");
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(responseEntity.getBody(), "First Name must not be empty.");
    }

    @Test
    public void registerAdmin_shouldThrowExceptionWhenEmailIsUsed() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");
        user.setIsAdmin(true);
        when(userRepository.findBySessionToken(any())).thenReturn(user);
        when(userRepository.findByEmail(any())).thenReturn(user);

        ResponseEntity responseEntity = userController.registerAdmin(user, "sessionToken");
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(responseEntity.getBody(), "Email already in use");
    }

    @Test
    public void registerAdmin_shouldReturnUser() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");
        user.setIsAdmin(true);
        when(userRepository.findBySessionToken(any())).thenReturn(user);
        when(userRepository.findByEmail(any())).thenReturn(null);

        ResponseEntity responseEntity = userController.registerAdmin(user, "sessionToken");
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(responseEntity.getBody(), "Register successful");
    }

    @Test
    public void user_shouldReturnUser() {
        User user = new User();
        user.setUuid("test");
        user.setEmail("test@test.com");
        user.setFirstName("testName");
        user.setLastName("testLastName");
        user.setPassword("testPassword");

        when(userRepository.findByUuid(any())).thenReturn(user);

        Assertions.assertEquals(userController.user("test"), user);
    }

    @Test
    public void login() {
    }

    @Test
    public void logout() {
    }

    @Test
    public void getUserBySessionToken() {
    }

    @Test
    public void registerModerator() {
    }

    @Test
    public void getUserBySessionTokenInJson() {
    }

    @Test
    public void isModerator() {
    }
}