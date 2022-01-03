package com.zealroom.room.booking.system.controllers;

import com.zealroom.room.booking.system.entities.Organization;
import com.zealroom.room.booking.system.entities.User;
import com.zealroom.room.booking.system.entities.UserOrganizationConnection;
import com.zealroom.room.booking.system.helpers.HelperService;
import com.zealroom.room.booking.system.repositories.OrganizationRepository;
import com.zealroom.room.booking.system.repositories.UserOrganizationConnectionRepository;
import com.zealroom.room.booking.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserOrganizationConnectionRepository uoc;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Organization newOrganization, @RequestHeader("session-token") String sessionToken) {
        User user = userRepository.findBySessionToken(sessionToken);
        if (user == null){
            return new ResponseEntity<>("Incorrect session-token header.",HttpStatus.BAD_REQUEST);
        }
        try{
            organizationRepository.save(newOrganization);
            UserOrganizationConnection userOrganizationConnection = new UserOrganizationConnection(newOrganization,user,true,"manager");
            uoc.save(userOrganizationConnection);

        }catch(DataIntegrityViolationException | IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Organization registered successfully.",HttpStatus.OK);
    }

    @PutMapping("/generate/inviteToken/{uuid}")
    public ResponseEntity generateInviteToken(@PathVariable String uuid,@RequestHeader("session-token") String sessionToken){
        Organization organization = organizationRepository.findByUuid(uuid);
        if (organization == null) {
            return new ResponseEntity<>("Incorrect uuid.", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findBySessionToken(sessionToken);
        if (user == null){
            return new ResponseEntity<>("Incorrect session-token header.",HttpStatus.BAD_REQUEST);
        }

        List<User> organizationUsers = uoc.getOrganizationUsers(uuid);
        if(!organizationUsers.contains(user)){
            return new ResponseEntity<>("Only users part of the organization can generate invite token.",HttpStatus.BAD_REQUEST);
        }
        String inviteToken = HelperService.generateNewToken();
        organization.setInviteToken(inviteToken);
        organizationRepository.save(organization);

        return new ResponseEntity<>(inviteToken,HttpStatus.OK);
    }

    @PutMapping("/generate/moderatorInviteToken/{uuid}")
    public ResponseEntity generateModeratorInviteToken(@PathVariable String uuid,@RequestHeader("session-token") String sessionToken){
        Organization organization = organizationRepository.findByUuid(uuid);
        if (organization == null) {
            return new ResponseEntity<>("Incorrect uuid.", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findBySessionToken(sessionToken);
        if (user == null){
            return new ResponseEntity<>("Incorrect session-token header.",HttpStatus.BAD_REQUEST);
        }

        List<User> organizationModerators = uoc.getOrganizationModerators(organization);
        if(!organizationModerators.contains(user)){
            return new ResponseEntity<>("Only moderators of the current organization can create moderator invite tokens",HttpStatus.BAD_REQUEST);
        }
        String inviteToken = HelperService.generateNewToken();
        organization.setModeratorInviteToken(inviteToken);
        organizationRepository.save(organization);

        return new ResponseEntity<>(inviteToken,HttpStatus.OK);
    }

    @PostMapping("/join/{inviteToken}")
    public ResponseEntity joinOrganization(@PathVariable String inviteToken, @RequestHeader("session-token") String sessionToken,@RequestBody String position){
        Organization organization = organizationRepository.findByInviteToken(inviteToken);
        if (organization == null){
            return new ResponseEntity<>("Incorrect invite token",HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findBySessionToken(sessionToken);
        if(user == null){
            return new ResponseEntity<>("Incorrect session-token header.",HttpStatus.BAD_REQUEST);
        }
        boolean moderatorRequest = organization.getModeratorInviteToken().equals(inviteToken);

        UserOrganizationConnection existing = uoc.getConnectionByUserAndOrganization(organization.getId(),user.getId());
        if((existing != null && !moderatorRequest) || (existing != null && existing.isManager() == true)){
            return new ResponseEntity<>("User is already in the organization with the correct permissions",HttpStatus.BAD_REQUEST);
        }

        String parsedPosition = HelperService.valueOfARepresentingKeyInJsonString("position",position);
        UserOrganizationConnection newUoc = new UserOrganizationConnection(organization,user,moderatorRequest,parsedPosition);

        String message;
        try{
            if(existing == null){
                uoc.save(newUoc);
                message = "Successfully joined organization";
            }else{
                existing.setManager(true);
                uoc.save(existing);
                message = "Successfully gained moderator permissions";
            }

        }catch(DataIntegrityViolationException | IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return  new ResponseEntity<>(message, HttpStatus.OK);
    }

}
