package com.zealroom.room.booking.system.controllers;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

///**
// * Creat an API endpoint to pass the home information from /models/***.java
// * "/api/v1"  is the path or the url that the application will respond to when a request is made.
// *
// *  v1 is for version 1
// *
// */
@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<?> getHome() {
        String result = "Test Request Zealroom Back-end!";
        return ResponseEntity.ok(result);
    }
}
