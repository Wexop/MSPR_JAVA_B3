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

    @GetMapping("/mes_plantes")
    List<Plante> getAllPlantesByIdUtilisateur(@RequestBody Integer utilisateur_id) {
        return repository.findByUtilisateur(utilisateur_id);
    }

    @PostMapping("/mes_plantes/{id}")
    Plante insertPlante(@PathVariable int id, @RequestBody Plante plante) { // id utilisateur NE SERA PAS dans le path mais en TOKEN
        var utilisateurFound = utilisateurRepository.findById(id).orElseThrow(() -> new Error("Aucun utilisateur avec l'id " + id));
        if (utilisateurFound != null) {
            plante.setUtilisateur(utilisateurFound);
        }
        return repository.save(plante);
    }
}
