package com.zealroom.room.booking.system.controllers;

import com.zealroom.room.booking.system.entities.*;
import com.zealroom.room.booking.system.repositories.BookingRepository;
import com.zealroom.room.booking.system.repositories.OrganizationRepository;
import com.zealroom.room.booking.system.repositories.UserOrganizationConnectionRepository;
import com.zealroom.room.booking.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zealroom.room.booking.system.exceptions.UserAuthenticationException;
import com.zealroom.room.booking.system.helpers.HelperService;

import java.util.List;

@RestController
@CrossOrigin(origins = "localhost:4200")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserOrganizationConnectionRepository userOrganizationConnectionRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private BookingRepository bookingRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User newUser) {
        if(!checkUser(newUser).equals("")){
            return new ResponseEntity<>(checkUser(newUser),HttpStatus.BAD_REQUEST);
        }
        if(userRepository.findByEmail(newUser.getEmail()) != null){
            return new ResponseEntity<>("Email already in use",HttpStatus.BAD_REQUEST);        }
        try{
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            newUser.setIsAdmin(false);
            userRepository.save(newUser);
            return new ResponseEntity<>("Register successful",HttpStatus.OK);
        } catch(DataIntegrityViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register/admin")
    public ResponseEntity registerAdmin(@RequestBody User newUser, @RequestHeader("session-token") String sessionToken) {
        User admin = userRepository.findBySessionToken(sessionToken);
        if(admin == null){
            return new ResponseEntity<>("Incorrect sessionToken",HttpStatus.BAD_REQUEST);
        }
        if(!admin.getIsAdmin()) {
            return new ResponseEntity<>("Only admins can create admin accounts",HttpStatus.BAD_REQUEST);
        }
        if(!checkUser(newUser).equals("")){
            return new ResponseEntity<>(checkUser(newUser),HttpStatus.BAD_REQUEST);
        }
        if(userRepository.findByEmail(newUser.getEmail()) != null){
            return new ResponseEntity<>("Email already in use",HttpStatus.BAD_REQUEST);
        }
        try{
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            newUser.setIsAdmin(true);
            userRepository.save(newUser);
            return new ResponseEntity<>("Register successful",HttpStatus.OK);
        }catch(DataIntegrityViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    private String checkUser(User user){
        String message = "";
        if(user.getFirstName() == null || user.getFirstName().equals("")){
            message = "First Name must not be empty.";
        }else if(user.getLastName() == null || user.getLastName().equals("")){
            message = "Last Name must not be empty.";
        }else if(user.getEmail() == null || user.getEmail().equals("")){
            message = "Email must not be empty.";
        }else if(user.getPassword() == null || user.getPassword().equals("")){
            message = "Password must not be empty.";
        }
        return message;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity user(@PathVariable String uuid) {
        User user = userRepository.findByUuid(uuid);
        if (user == null){
            return new ResponseEntity<>("Incorrect uuid.",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/login")
    public ResponseEntity login(@RequestBody ObjectNode emailAndPasswordInJson) {
        String email = emailAndPasswordInJson.get("email").asText();
        String password = emailAndPasswordInJson.get("password").asText();
        User user;
        try{
            user = authenticateAndReturnUser(email, password);
            user.setSessionToken(HelperService.generateNewToken());
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserAuthenticationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/logout")
    public ResponseEntity logout(@RequestHeader("session-token") String sessionToken){
        User user = userRepository.findBySessionToken(sessionToken);
        if(user == null){
            return new ResponseEntity<>("Incorrect sessionToken",HttpStatus.BAD_REQUEST);

        }
        user.setSessionToken(null);
        userRepository.save(user);
        return new ResponseEntity<>("Logout successful",HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity getUserBySessionToken(@RequestHeader("session-token") String sessionToken){
        User user = userRepository.findBySessionToken(sessionToken);
        if(user == null){
            return new ResponseEntity<>("Incorrect sessionToken",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/get/organizations")
    public ResponseEntity getUserOrganizations(@RequestHeader("session-token") String sessionToken){
        User user = userRepository.findBySessionToken(sessionToken);
        if(user == null){
            return new ResponseEntity<>("Incorrect sessionToken.",HttpStatus.BAD_REQUEST);
        }
        List<Organization> organizations = userOrganizationConnectionRepository.getUserOrganizations(user.getId());
        return new ResponseEntity<>(organizations,HttpStatus.OK);
    }

    @GetMapping("/get/bookings")
    public ResponseEntity getUserBookings(@RequestHeader("session-token")  String sessionToken){
        User user = userRepository.findBySessionToken(sessionToken);
        if(user == null){
            return new ResponseEntity<>("Incorrect sessionToken.", HttpStatus.BAD_REQUEST);
        }
        List<Booking> bookings = bookingRepository.findAllByUserId(user.getId());

        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity deleteUser(@PathVariable String uuid, @RequestHeader("session-token") String sessionToken){
        User admin = userRepository.findBySessionToken(sessionToken);
        if(admin == null){
            return new ResponseEntity<>("Incorrect sessionToken",HttpStatus.BAD_REQUEST);
        }
        if(!admin.getIsAdmin()){
            return new ResponseEntity<>("Only admins can delete users",HttpStatus.BAD_REQUEST);
        }

        User toBeDeleted = userRepository.findByUuid(uuid);
        if(toBeDeleted == null){
            return new ResponseEntity<>("Incorrect uuid",HttpStatus.BAD_REQUEST);
        }
        userRepository.delete(toBeDeleted);
        return new ResponseEntity<>("User deleted",HttpStatus.OK);

    }

    @PostMapping("/register/moderator/{organizationUuid}")
    public ResponseEntity registerModerator(@RequestBody User newUser, @RequestHeader("session-token") String sessionToken, @PathVariable String organizationUuid) {
        Organization organization = organizationRepository.findByUuid(organizationUuid);
        if(organization == null){
            return new ResponseEntity<>("Wrong organization uuid",HttpStatus.BAD_REQUEST);
        }
        User moderator = userRepository.findBySessionToken(sessionToken);
        if(moderator == null){
            return new ResponseEntity<>("Incorrect session token",HttpStatus.BAD_REQUEST);
        }
        if(!isModerator(moderator,organization)) {
            return new ResponseEntity<>("Only moderators can add moderator accounts",HttpStatus.BAD_REQUEST);
        }
        if(!checkUser(newUser).equals("")){
            return new ResponseEntity<>(checkUser(newUser),HttpStatus.BAD_REQUEST);
        }
        if(userRepository.findByEmail(newUser.getEmail()) != null){
            return new ResponseEntity<>("Email already in use",HttpStatus.BAD_REQUEST);
        }
        try{
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            newUser.setIsAdmin(false);
            userRepository.save(newUser);
            UserOrganizationConnection userOrganizationConnection = new UserOrganizationConnection(organization,newUser,true,"manager");
            userOrganizationConnectionRepository.save(userOrganizationConnection);
            return new ResponseEntity<>("Manager creation successful",HttpStatus.OK);
        }catch(DataIntegrityViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    private User authenticateAndReturnUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UserAuthenticationException("Wrong email or password.");
        }
        if(encoder.matches(password, user.getPassword())) {
            return user;
        }else {
            throw new UserAuthenticationException("Wrong email or password.");
        }
    }

    public boolean isModerator(User user, Organization organization){
       List<User> moderators = userOrganizationConnectionRepository.getOrganizationModerators(organization);

       if(moderators.contains(user)){
           return true;
       }
       return false;
    }

    @GetMapping("/getOrganizationUsers/{uuid}")
    public ResponseEntity getUsersInOrganization(@PathVariable String uuid){

        List<User> users = userOrganizationConnectionRepository.findUsersByOrganizationId(uuid);


        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}