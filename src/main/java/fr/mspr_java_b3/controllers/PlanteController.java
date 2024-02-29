package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.controllers.requests_body.PutPlanteRequest;
import fr.mspr_java_b3.entities.Plante;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.PlanteRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearer")
public class PlanteController {

    private final PlanteRepository repository;

    private final UtilisateurRepository utilisateurRepository;

    PlanteController(PlanteRepository repository, UtilisateurRepository utilisateurRepository) {
        this.repository = repository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/mes_plantes")
    List<Plante> getAllPlantesByIdUtilisateur(@RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {
        return repository.findByUtilisateur(Integer.parseInt(authorizationHeader));
    }

    @PostMapping("/plante/one")
    Plante postPlante(@RequestBody Plante plante, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        Utilisateur utilisateur = utilisateurRepository.findById(Integer.parseInt(authorizationHeader)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvale"));
        plante.setUtilisateur(utilisateur);

        return repository.save(plante);
    }

    @PutMapping("/plante/{id}")
    Plante putPlante(@RequestBody PutPlanteRequest entity, @PathVariable(name = "id") Integer id) {

        Plante initialEntity = repository.getReferenceById(id);

        if(entity.espece != null) initialEntity.setEspece(entity.espece);
        if(entity.image_url != null) initialEntity.setImage_url(entity.image_url);

        return repository.save(initialEntity);

    }

    @DeleteMapping("/plante/{id}")
    boolean deletePlante(@PathVariable(name = "id") Integer id) {

        try {
            repository.deleteById(id);
            return true;
        } catch (Error error) {
            return false;
        }
    }
}
