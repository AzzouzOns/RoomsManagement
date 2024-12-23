package com.monsite.reservation.roomreservation.service;

import com.monsite.reservation.roomreservation.entities.User;
import com.monsite.reservation.roomreservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);  // Assurez-vous que cela fonctionne correctement
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);  // Retourne null si l'utilisateur n'est pas trouvé
    }

    public void updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        // Mettez à jour les autres champs selon les besoins
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
