package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.controllers.requests_body.PutPlanteRequest;
import fr.mspr_java_b3.entities.Plante;
import fr.mspr_java_b3.repository.PlanteRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PlanteController {

    private final PlanteRepository repository;

    private final UtilisateurRepository utilisateurRepository;

    PlanteController(PlanteRepository repository, UtilisateurRepository utilisateurRepository) {
        this.repository = repository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/mes_plantes")
    List<Plante> getAllPlantesByIdUtilisateur(@RequestHeader(value = "Utilisateur_id") String authorizationHeader) {
        return repository.findByUtilisateur(Integer.parseInt(authorizationHeader));
    }

    @PostMapping("/plante/one")
    Plante postPlante(@RequestBody Plante plante) {
        return repository.save(plante);
    }

    @PutMapping("/plante/{id}")
    Plante putPlante(@RequestBody PutPlanteRequest entity, @PathVariable(name = "id") Integer id) {

        Plante initialEntity = repository.getReferenceById(id);

        initialEntity.setEspece(entity.espece);
        initialEntity.setImage_url(entity.image_url);

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
