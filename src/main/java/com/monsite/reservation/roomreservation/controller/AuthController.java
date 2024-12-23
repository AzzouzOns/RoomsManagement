package com.monsite.reservation.roomreservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/")
    public String goToHomePage() {
        return "redirect:/rooms";
    }
}