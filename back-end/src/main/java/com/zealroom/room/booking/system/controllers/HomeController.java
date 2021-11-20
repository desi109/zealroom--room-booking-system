package com.zealroom.room.booking.system.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String getHpme() {
        return "Home Page Test!";
    }
}
