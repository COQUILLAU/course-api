package com.course.courseapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "achats")
public class Achat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_achat;

    private String description;

    private Double montant;

    @Column(name = "achat_date")
    private java.sql.Date achatDate;

    @ManyToOne
    @JoinColumn(name = "id_budget", nullable = false)
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
}
