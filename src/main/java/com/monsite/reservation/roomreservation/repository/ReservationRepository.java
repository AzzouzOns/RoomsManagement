package com.monsite.reservation.roomreservation.repository;

import com.monsite.reservation.roomreservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.guestName LIKE %:query% OR r.guestEmail LIKE %:query%")
    List<Reservation> searchReservations(String query);

}

