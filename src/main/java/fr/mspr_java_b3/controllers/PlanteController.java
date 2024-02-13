package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.Plante;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.PlanteRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlanteController {

    private final PlanteRepository repository;

    private final UtilisateurRepository utilisateurRepository;

    PlanteController(PlanteRepository repository, UtilisateurRepository utilisateurRepository) {
        this.repository = repository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/mes_plantes/{utilisateur_id}")
    List<Plante> getAllPlantesByIdUtilisateur(@PathVariable Integer utilisateur_id) {
        return repository.findByUtilisateur(utilisateur_id);
    }
}
