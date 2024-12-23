package com.monsite.reservation.roomreservation.service;

import com.monsite.reservation.roomreservation.entities.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomService {

    List<Room> getAllRooms();

    Room addRoom(Room room);

    Room updateRoom(Long id, Room updatedRoom);

    void deleteRoom(Long id);

    Room getRoomById(Long id);

    List<Room> searchRooms(String query);

    Page<Room> getRoomsByPage(int page, int size);


}
