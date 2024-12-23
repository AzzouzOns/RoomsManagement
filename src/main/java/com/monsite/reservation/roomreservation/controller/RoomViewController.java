package com.monsite.reservation.roomreservation.controller;

import com.monsite.reservation.roomreservation.entities.Room;
import com.monsite.reservation.roomreservation.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms/view")
public class RoomViewController {

    private final RoomService roomService;

    public RoomViewController(RoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping
    public String showRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms()); // Charger les données
        return "rooms";
    }


    @GetMapping("/edit/{id}")
    public String editRoom(@PathVariable Long id, Model model) {
        Room room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        return "edit-room";
    }
}
