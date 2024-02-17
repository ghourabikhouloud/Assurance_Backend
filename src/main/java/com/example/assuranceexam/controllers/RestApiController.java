package com.example.assuranceexam.controllers;

import com.example.assuranceexam.entities.Assurance;
import com.example.assuranceexam.entities.Benificaire;
import com.example.assuranceexam.entities.Contrat;
import com.example.assuranceexam.entities.TypeContrat;
import com.example.assuranceexam.services.Iservice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("rest")
@RestController
@CrossOrigin(origins = "*")
public class RestApiController {

    private final Iservice iservice;


    @PostMapping("/addB")
    public Benificaire addBloc(@RequestBody Benificaire b){
        return iservice.ajouterBeneficiaire(b);
    }

    @PostMapping("/addC")
    public Contrat ajouterContrat(@RequestBody Contrat c){
        return iservice.ajouterContrat(c);
    }

    @PostMapping("/ajouterAssurance/{cinBf}/{matricule}")
    public Assurance ajouterAssurance(@RequestBody Assurance a, @PathVariable int cinBf, @PathVariable String matricule){
        return iservice.ajouterAssurance(a, cinBf, matricule);
    }

    @GetMapping("/getContratBf/{idBf}")
    public Contrat getContratBf(@PathVariable int idBf){
        return iservice.getContratBf(idBf);
    }

    @GetMapping("/getBeneficairesByType/{typeContrat}")
    public Set<Benificaire> getBeneficairesByType(@PathVariable TypeContrat typeContrat){
        return iservice.getBeneficairesByType(typeContrat);
    }

    @GetMapping("/getMontantBf/{cinBf}")
    public float getMontantBf(@PathVariable int cinBf){
        return iservice.getMontantBf(cinBf);
    }

    @GetMapping("/statistiques")
    public void statistiques(){
         iservice.statistiques();
    }


}
