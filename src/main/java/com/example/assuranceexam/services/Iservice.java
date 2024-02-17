package com.example.assuranceexam.services;

import com.example.assuranceexam.entities.Assurance;
import com.example.assuranceexam.entities.Benificaire;
import com.example.assuranceexam.entities.Contrat;
import com.example.assuranceexam.entities.TypeContrat;

import java.util.Set;

public interface Iservice {
    public Benificaire ajouterBeneficiaire (Benificaire bf);

    public Contrat ajouterContrat (Contrat c);

    public Assurance ajouterAssurance (Assurance a, int cinBf, String matricule);

    public Contrat getContratBf (int idBf);

    public Set<Benificaire> getBeneficairesByType (TypeContrat typeContrat);

    public float getMontantBf (int cinBf);

    public void statistiques ( );
}
