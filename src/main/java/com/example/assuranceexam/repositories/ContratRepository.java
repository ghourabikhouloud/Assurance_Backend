package com.example.assuranceexam.repositories;

import com.example.assuranceexam.entities.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratRepository extends JpaRepository<Contrat, Integer> {
    Contrat findByMatricule(String martricule);


}