package com.monsite.reservation.roomreservation.service;

import com.monsite.reservation.roomreservation.entities.Reservation;
import com.monsite.reservation.roomreservation.entities.Room;
import com.monsite.reservation.roomreservation.repository.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aucune réservation trouvée avec l'ID : " + id));
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        if (reservation.getStartDate().isAfter(reservation.getEndDate())) {
            throw new IllegalArgumentException("La date de début ne peut pas être après la date de fin.");
        }
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Réservation introuvable avec l'ID : " + id));


        if (updatedReservation.getGuestName() != null) {
            existingReservation.setGuestName(updatedReservation.getGuestName());
        }
        if (updatedReservation.getGuestEmail() != null) {
            existingReservation.setGuestEmail(updatedReservation.getGuestEmail());
        }
        if (updatedReservation.getStartDate() != null) {
            existingReservation.setStartDate(updatedReservation.getStartDate());
        }
        if (updatedReservation.getEndDate() != null) {
            existingReservation.setEndDate(updatedReservation.getEndDate());
        }
        if (updatedReservation.getClientName() != null) {
            existingReservation.setClientName(updatedReservation.getClientName());
        }
        if (updatedReservation.getStatus() != null) {
            existingReservation.setStatus(updatedReservation.getStatus());
        }
        if (updatedReservation.getRoom() != null) {
            existingReservation.setRoom(updatedReservation.getRoom());
        }

        return reservationRepository.save(existingReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Réservation introuvable avec l'ID : " + id));
        reservationRepository.delete(reservation);
    }

    @Override
    public Page<Reservation> getReservationByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reservationRepository.findAll(pageable); // Retourne les réservations avec pagination
    }

    @Override
    public List<Reservation> searchReservations(String query) {
        // Implémentation à faire pour la recherche, par exemple via un critère spécifique
        return reservationRepository.searchReservations(query);
    }

    @Override
    public Reservation findById(Long id) {
        return getReservationById(id); // Utilisation de getReservationById pour une recherche plus générique
    }
}