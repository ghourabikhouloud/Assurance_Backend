package com.example.assuranceexam.repositories;

import com.example.assuranceexam.entities.Assurance;
import com.example.assuranceexam.entities.Benificaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssuranceRepository extends JpaRepository<Assurance, Integer> {

    int countByBenificaire(Benificaire benificaire);

}