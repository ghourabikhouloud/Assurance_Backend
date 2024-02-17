package com.example.assuranceexam.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Contrat  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContrat;

        private String matricule;

    private LocalDate dateEffet;

    @Enumerated(EnumType.STRING)
    private TypeContrat type;



}
