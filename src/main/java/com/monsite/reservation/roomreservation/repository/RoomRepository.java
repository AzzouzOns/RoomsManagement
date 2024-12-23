package com.monsite.reservation.roomreservation.repository;

import com.monsite.reservation.roomreservation.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {


    boolean existsByName(String name);

    List<Room> findByNameContainingIgnoreCaseOrTypeContainingIgnoreCase(String name, String type);

}
