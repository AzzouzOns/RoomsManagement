package com.monsite.reservation.roomreservation.service;

import com.monsite.reservation.roomreservation.entities.Reservation;
import com.monsite.reservation.roomreservation.entities.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReservationService {

    List<Reservation> getAllReservations();

    Reservation addReservation(Reservation reservation);

    Reservation updateReservation(Long id, Reservation updatedReservation);

    void deleteReservation(Long id);

    Reservation getReservationById(Long id);

    List<Reservation> searchReservations(String query);

    Page<Reservation> getReservationByPage(int page, int size);

    Reservation findById(Long id);
}