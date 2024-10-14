package com.course.courseapi.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "salaires")
public class Salaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_salaire;

    private Double montant;


    @Column(nullable = false)
    private LocalDate salaireDate;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
}
