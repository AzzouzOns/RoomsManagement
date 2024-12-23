package com.monsite.reservation.roomreservation.controller;

import com.monsite.reservation.roomreservation.entities.Reservation;
import com.monsite.reservation.roomreservation.entities.Room;
import com.monsite.reservation.roomreservation.service.ReservationService;
import com.monsite.reservation.roomreservation.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final RoomService roomService;

    public ReservationController(ReservationService reservationService, RoomService roomService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
    }


    @GetMapping
    public String getAllReservations(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     Model model) {
        Page<Reservation> reservationPage = reservationService.getReservationByPage(page, size);
        model.addAttribute("reservations", reservationPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", reservationPage.getTotalPages());
        model.addAttribute("noReservations", reservationPage.getTotalElements() == 0);
        return "reservations";
    }


    @GetMapping({"/add", "/edit/{id}"})
    public String showReservationForm(@PathVariable(value = "id", required = false) Long id, Model model) {
        Reservation reservation = id != null ? reservationService.findById(id) : new Reservation();
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("reservation", reservation);
        model.addAttribute("rooms", rooms);
        return "add-reservation";
    }


    @PostMapping("/add")
    public String addReservation(@ModelAttribute Reservation reservation) {
        reservationService.addReservation(reservation);
        return "redirect:/reservations";
    }

    @PostMapping("/update")
    public String updateReservation(@ModelAttribute Reservation reservation) {
        reservationService.updateReservation(reservation.getId(), reservation);
        return "edit-reservation.";
    }


    @GetMapping("/{id}")
    public String getReservationById(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id);
        model.addAttribute("reservation", reservation);
        return "reservation-details";
    }

    @GetMapping("/delete/{id}")
    public String deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservations";
    }


    @GetMapping("/search")
    public String searchReservations(@RequestParam("query") String query, Model model) {
        List<Reservation> reservations = reservationService.searchReservations(query);
        model.addAttribute("reservations", reservations);
        return "reservations";
    }
}
