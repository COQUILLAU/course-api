package com.course.courseapi.service;

import com.course.courseapi.model.Log;
import com.course.courseapi.model.User;
import com.course.courseapi.repository.UserRepository;
import com.course.courseapi.repository.LogRepository; // Assurez-vous d'importer le dépôt de logs
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.course.courseapi.exception.UsernameAlreadyExistsException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LogRepository logRepository; // Déclaration du dépôt de logs

    public void registerUser(User user) {
        // Vérifier si le nom d'utilisateur existe déjà
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username is already taken.");
        }
        
        // Vérifier si l'email existe déjà
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UsernameAlreadyExistsException("Email is already in use.");
        }
    
        // Encoder le mot de passe et enregistrer l'utilisateur
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now()); // Définir la date de création
        userRepository.save(user);
        
        // Enregistrement dans les logs
        Log log = new Log();
        log.setUser(user); // Assurez-vous que setUser accepte un User
        log.setAction("User registered");
        log.setTimestamp(LocalDateTime.now());
        logRepository.save(log);
    }

    public User loginUser(String identifier, String password) {
        Optional<User> userOptional = userRepository.findByEmail(identifier);
        if (userOptional.isEmpty()) {
            userOptional = userRepository.findByUsername(identifier);
        }

        // Vérification de l'utilisateur et du mot de passe
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Enregistrement dans les logs pour une connexion réussie
                Log log = new Log();
                log.setUser(user);
                log.setAction("User logged in");
                log.setTimestamp(LocalDateTime.now());
                logRepository.save(log);
                
                return user; // Connexion réussie, retourner l'utilisateur
            } else {
                // Enregistrement dans les logs pour une tentative de connexion échouée
                Log log = new Log();
                log.setUser(user);
                log.setAction("Failed login attempt");
                log.setTimestamp(LocalDateTime.now());
                logRepository.save(log);
            }
        }
        return null; // Connexion échouée
    }
}
