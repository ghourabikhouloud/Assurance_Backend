package com.example.assuranceexam.services;

import com.example.assuranceexam.entities.Assurance;
import com.example.assuranceexam.entities.Benificaire;
import com.example.assuranceexam.entities.Contrat;
import com.example.assuranceexam.entities.TypeContrat;
import com.example.assuranceexam.repositories.AssuranceRepository;
import com.example.assuranceexam.repositories.BenificaireRepository;
import com.example.assuranceexam.repositories.ContratRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Primary
@AllArgsConstructor
@Service
@Slf4j
public class ImplService implements Iservice {
    AssuranceRepository assuranceRepo;
    BenificaireRepository benificaireRepo;
    ContratRepository contratRepos;

    @Override
    public Benificaire ajouterBeneficiaire(Benificaire bf) {
        return benificaireRepo.save(bf);
    }

    @Override
    public Contrat ajouterContrat(Contrat c) {
        return contratRepos.save(c);
    }

    @Override
    @Transactional
    public Assurance ajouterAssurance(Assurance a, int cinBf, String matricule) {
        Benificaire b = benificaireRepo.findByCin(cinBf);
        Contrat  c = contratRepos.findByMatricule(matricule);

        if(b!=null && c!=null && a!=null) {
            assuranceRepo.save(a);
            a.setContrat(c);
            a.setBenificaire(b);
            return a;
        }
        return null;
    }

    @Override
        public Contrat getContratBf(int idBf) {
        Benificaire b = benificaireRepo.findById(idBf).orElse(null);
        if(b!=null){
            List<Assurance> assurancesList = new ArrayList<>(b.getAssurances());

            LocalDate date =assurancesList.get(0).getContrat().getDateEffet();

            Contrat c=null;

            for (Assurance a : b.getAssurances()) {
                if(a.getContrat().getDateEffet().isBefore(date)){
                    date=a.getContrat().getDateEffet();
                    c=a.getContrat();
                }
            }

            return c;
        }
return null;
    }

    @Override
    public Set<Benificaire> getBeneficairesByType(TypeContrat typeContrat) {
        return benificaireRepo.findByAssurances_Contrat_Type(typeContrat);
    }

    @Override
    public float getMontantBf(int cinBf) {
        Benificaire b = benificaireRepo.findByCin(cinBf);
        float montant = 0;
        if (b != null) {
            for (Assurance a : b.getAssurances()) {
                if (a.getContrat().getType() == TypeContrat.ANNUEL) {
                    montant += (a.getMontant());

                } else if (a.getContrat().getType() == TypeContrat.SEMESTRIEL) {

                    montant += (a.getMontant() * 2);

                } else if (a.getContrat().getType() == TypeContrat.MENSUEL) {

                    montant += (a.getMontant() * 12);

                }
            }

            return montant;
        }
        return -1;
    }

/*
    @Override
    @Scheduled(fixedRate = 60000)
    public void statistiques() {
        List<Benificaire> benificaires = benificaireRepo.findAll();

        for (Benificaire b : benificaires) {
            int nbAssurance = assuranceRepo.countByBenificaire(b);
            log.info("Number of assurances for beneficiary " + b.getNom() + ": " + nbAssurance);
        }
    }


 */


    @Override
    @Scheduled(fixedRate = 60000) 
    public void statistiques() {
        //      nbAssu   cinBf
        TreeMap<Integer, Integer> myStat = new TreeMap<>(Collections.reverseOrder());

        List<Benificaire> benificaires = benificaireRepo.findAll();

        for (Benificaire b : benificaires) {
            myStat.put(assuranceRepo.countByBenificaire(b), b.getCin());
        }

        for (Map.Entry<Integer, Integer> entry : myStat.entrySet()) {
            log.info("Number of assurances for beneficiary cin : " + entry.getValue() + " : " + entry.getKey());
        }

    }
}
