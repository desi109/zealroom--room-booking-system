package com.zealroom.room.booking.system.controllers;

import com.zealroom.room.booking.system.entities.Organization;
import com.zealroom.room.booking.system.entities.UserOrganizationConnection;
import com.zealroom.room.booking.system.repositories.OrganizationRepository;
import com.zealroom.room.booking.system.repositories.UserOrganizationConnectionRepository;
import com.zealroom.room.booking.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.node.ObjectNode;

import com.zealroom.room.booking.system.entities.User;
import com.zealroom.room.booking.system.exceptions.UserAuthenticationException;
import com.zealroom.room.booking.system.helpers.HelperService;

import java.util.List;

@RestController
@CrossOrigin(origins = "localhost:3001")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserOrganizationConnectionRepository userOrganizationConnectionRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    private String register(@RequestBody User newUser) {
        if(!checkUser(newUser).equals("")){
            return checkUser(newUser);
        }
        if(userRepository.findByEmail(newUser.getEmail()) != null){
            return HelperService.toJson("error", "Email already in use.");
        }
        try{
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            newUser.setIsAdmin(false);
            userRepository.save(newUser);
            return HelperService.toJson("message","Register successful!");
        }catch(DataIntegrityViolationException e){
            return HelperService.toJson("error", e.getMessage());
        }
    }

    @PostMapping("/register/admin/{sessionToken}")
    private String registerAdmin(@RequestBody User newUser, @PathVariable String sessionToken) {
        User admin = userRepository.findBySessionToken(sessionToken);
        if(admin == null){
            return HelperService.toJson("error", "Incorrect session token.");
        }
        if(!admin.getIsAdmin()) {
            return HelperService.toJson("error", "Only admins can add admin accounts !");
        }
        if(!checkUser(newUser).equals("")){
            return checkUser(newUser);
        }
        if(userRepository.findByEmail(newUser.getEmail()) != null){
            return HelperService.toJson("error", "Email already in use.");
        }
        try{
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            newUser.setIsAdmin(true);
            userRepository.save(newUser);
            return HelperService.toJson("message","Register successful!");
        }catch(DataIntegrityViolationException e){
            return HelperService.toJson("error", e.getMessage());
        }
    }

    private String checkUser(User user){
        String message = "";
        if(user.getFirstName() == ""){
            message = "First Name must not be empty.";
        }else if(user.getLastName() == ""){
            message = "Last Name must not be empty.";
        }else if(user.getEmail() == ""){
            message = "Email must not be empty.";
        }else if(user.getPassword() == ""){
            message = "Password must not be empty.";
        }
        if(message.equals("")){
            return "";
        }else{
            return HelperService.toJson("error",message);
        }
    }

    @GetMapping("/{uuid}")
    private User user(@PathVariable String uuid) {
        return userRepository.findByUuid(uuid);
    }

    @PutMapping("/login")
    private User login(@RequestBody ObjectNode emailAndPasswordInJson) {
        String email = emailAndPasswordInJson.get("email").asText();
        String password = emailAndPasswordInJson.get("password").asText();
        User user;
        try{
            user = authenticateAndReturnUser(email, password);
            String newSessionToken = HelperService.generateNewToken();
            user.setSessionToken(newSessionToken);
            userRepository.save(user);
            return user;
        }catch(UserAuthenticationException e){
            return null;
        }
    }

    @PutMapping("/logout")
    private String logout(@RequestBody String sessionToken){
        User user = getUserBySessionTokenInJson(userRepository,sessionToken);
        if(user == null){
            return HelperService.toJson("error","Incorrect sessionToken");
        }
        user.setSessionToken(null);
        userRepository.save(user);
        return HelperService.toJson("success", "Logout successful.");
    }

    @GetMapping("/get")
    private User getUserBySessionToken(@RequestBody String sessionToken){
        return getUserBySessionTokenInJson(userRepository,sessionToken);
    }

    @PostMapping("/register/moderator/{sessionToken}/{organizationUuid}")
    private String registerModerator(@RequestBody User newUser, @PathVariable String sessionToken, @PathVariable String organizationUuid) {
        Organization organization = organizationRepository.findByUuid(organizationUuid);
        if(organization == null){
            return HelperService.toJson("error", "Incorrect organization uuid.");
        }
        User moderator = userRepository.findBySessionToken(sessionToken);
        if(moderator == null){
            return HelperService.toJson("error", "Incorrect session token.");
        }
        if(!isModerator(moderator,organization)) {
            return HelperService.toJson("error", "Only moderators can add moderator accounts !");
        }
        if(!checkUser(newUser).equals("")){
            return checkUser(newUser);
        }
        if(userRepository.findByEmail(newUser.getEmail()) != null){
            return HelperService.toJson("error", "Email already in use.");
        }
        try{
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            newUser.setIsAdmin(false);
            userRepository.save(newUser);
            UserOrganizationConnection userOrganizationConnection = new UserOrganizationConnection(organization,newUser,true);
            userOrganizationConnectionRepository.save(userOrganizationConnection);
            return HelperService.toJson("message","Manager creation successful!");
        }catch(DataIntegrityViolationException e){
            return HelperService.toJson("error", e.getMessage());
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

    public static User getUserBySessionTokenInJson(UserRepository userRepository,String jsonBody) {
        String sessionToken = HelperService.valueOfARepresentingKeyInJsonString("sessionToken",jsonBody);
        return userRepository.findBySessionToken(sessionToken);
    }

    public boolean isModerator(User user, Organization organization){
       List<User> moderators = userOrganizationConnectionRepository.getOrganizationModerators(organization);

       if(moderators.contains(user)){
           return true;
       }
       return false;
    }

}