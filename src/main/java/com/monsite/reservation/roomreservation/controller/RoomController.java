package com.monsite.reservation.roomreservation.controller;

import com.monsite.reservation.roomreservation.entities.Room;
import com.monsite.reservation.roomreservation.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping
    public String getAllRooms(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              Model model) {
        Page<Room> roomPage = roomService.getRoomsByPage(page, size);
        model.addAttribute("rooms", roomPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", roomPage.getTotalPages());
        return "rooms";
    }


    @GetMapping("/add-room")
    public String showAddRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "add-room";
    }


    @PostMapping("/add-room")
    public String addRoom(@ModelAttribute Room room) {
        roomService.addRoom(room);
        return "redirect:/rooms";
    }


    @GetMapping("/{id}")
    public String getRoomById(@PathVariable Long id, Model model) {
        Room room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        return "room-details";
    }


    @GetMapping("/edit/{id}")
    public String editRoom(@PathVariable Long id, Model model) {
        Room room = roomService.getRoomById(id);
        if (room == null) {
            model.addAttribute("error", "Chambre non trouvée");
            return "redirect:/rooms";
        }
        model.addAttribute("room", room);
        return "edit-room";
    }


    @PostMapping("/update")
    public String updateRoom(@ModelAttribute Room room, Model model) {
        if (room.getId() == null) {
            model.addAttribute("error", "ID de la chambre invalide");
            return "redirect:/rooms";
        }
        roomService.updateRoom(room.getId(), room);
        return "redirect:/rooms";
    }

    // Supprimer une chambre
    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        try {
            roomService.deleteRoom(id);
            return "redirect:/rooms";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/search")
    public String searchRooms(@RequestParam("query") String query, Model model) {
        List<Room> rooms = roomService.searchRooms(query);
        model.addAttribute("rooms", rooms);
        return "rooms";
    }
}
