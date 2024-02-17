package com.example.assuranceexam.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@Entity
public class Benificaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBenef;

    private int cin;

    private String nom;

    private String prenom;

    private String profession;

    private Float salaire;

    @JsonIgnore
    @OneToMany( mappedBy = "benificaire",fetch = FetchType.EAGER)
    Set<Assurance> assurances;

}
