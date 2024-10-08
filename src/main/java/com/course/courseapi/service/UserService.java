package com.course.courseapi.service;

import com.course.courseapi.model.User;
import com.course.courseapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, String newUsername, String newPassword) {
        // Étape 1 : Récupérer l'utilisateur
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Étape 2 : Modifier les propriétés de l'utilisateur
        user.setUsername(newUsername);
        user.setPassword(newPassword);

        // Étape 3 : Enregistrer l'utilisateur (cela met à jour l'entité)
        return userRepository.save(user);
    }
}
