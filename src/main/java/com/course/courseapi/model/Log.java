package com.course.courseapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_log;
    
    private String action;
    private LocalDateTime timestamp;
    private String token;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    // Getters et setters
}

