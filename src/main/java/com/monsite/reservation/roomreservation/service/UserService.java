package com.monsite.reservation.roomreservation.service;

import com.monsite.reservation.roomreservation.entities.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User addUser(User user);
    User updateUser(Long id, User updatedUser);
    User getUserById(Long id);
    boolean deleteUser(Long id);
}
