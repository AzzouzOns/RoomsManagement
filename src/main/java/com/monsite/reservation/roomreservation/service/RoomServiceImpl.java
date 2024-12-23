package com.monsite.reservation.roomreservation.service;

import com.monsite.reservation.roomreservation.entities.Room;
import com.monsite.reservation.roomreservation.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Long id, Room updatedRoom) {
        return roomRepository.findById(id).map(existingRoom -> {
            existingRoom.setName(updatedRoom.getName());
            existingRoom.setCapacity(updatedRoom.getCapacity());
            existingRoom.setDescription(updatedRoom.getDescription());
            existingRoom.setAvailable(updatedRoom.isAvailable());
            existingRoom.setType(updatedRoom.getType());
            existingRoom.setImageUrl(updatedRoom.getImageUrl());
            return roomRepository.save(existingRoom);
        }).orElseThrow(() -> new RuntimeException("Room not found for id: " + id));
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found for id: " + id));
    }

    @Override
    public List<Room> searchRooms(String query) {
        return roomRepository.findByNameContainingIgnoreCaseOrTypeContainingIgnoreCase(query, query);
    }

    @Override
    public Page<Room> getRoomsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return roomRepository.findAll(pageable);
    }

}


