package com.course.courseapi.repository;

import com.course.courseapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Tu peux ajouter des méthodes supplémentaires ici si nécessaire
    User findByUsername(String username);
}
