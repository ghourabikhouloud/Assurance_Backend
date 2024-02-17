package com.example.assuranceexam.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
public class Assurance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAssurance;

    private String designation;

    private Float montant;

    @ManyToOne
    private Contrat contrat;

    @ManyToOne
    private Benificaire benificaire;



}
