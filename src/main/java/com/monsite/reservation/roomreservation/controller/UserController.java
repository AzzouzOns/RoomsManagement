package com.monsite.reservation.roomreservation.controller;

import com.monsite.reservation.roomreservation.entities.User;
import com.monsite.reservation.roomreservation.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/users";

    }

    @GetMapping("")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    // Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-details";
    }


    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable Long id, @ModelAttribute User updatedUser) {
        userService.updateUser(id, updatedUser);
        return "redirect:/users";
    }


    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
