package com.example.assuranceexam.repositories;

import com.example.assuranceexam.entities.Benificaire;
import com.example.assuranceexam.entities.TypeContrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface BenificaireRepository extends JpaRepository<Benificaire, Integer> {
    Benificaire findByCin(int cin);

    Set<Benificaire> findByAssurances_Contrat_Type(TypeContrat type);





    //List<Benificaire> (int cin);
}