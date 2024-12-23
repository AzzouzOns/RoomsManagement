package com.monsite.reservation.roomreservation.controller;

import com.monsite.reservation.roomreservation.entities.Reservation;
import com.monsite.reservation.roomreservation.entities.Room;
import com.monsite.reservation.roomreservation.service.ReservationService;
import com.monsite.reservation.roomreservation.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservations/view")
public class ReservationViewController {

    private final ReservationService reservationService;

    public ReservationViewController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @GetMapping
    public String showReservations(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "reservations";
    }


    @GetMapping("/edit/{id}")
    public String editReservation(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id);
        model.addAttribute("reservation", reservation);
        return "edit-reservation.";
    }
}
