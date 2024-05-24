package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.Annonce;
import fr.mspr_java_b3.entities.Proposition;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.AnnonceRepository;
import fr.mspr_java_b3.repository.PropositionRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearer")
public class    PropositionControllerAChanger {

    private final PropositionRepository repository;
    private final AnnonceRepository annonceRepository;

    PropositionControllerAChanger(PropositionRepository repository, AnnonceRepository annonceRepository) {
        this.repository = repository;
        this.annonceRepository = annonceRepository;
    }

    @GetMapping("/proposition/{id}")
    Proposition getProposition(@PathVariable int id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de proposition avec l'id " + id));
    }

    @PostMapping("/proposition/annonce/{annonce_id}")
    Proposition postProposition(@RequestBody Proposition proposition, @PathVariable int annonce_id, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        Annonce annonce = annonceRepository.findById(annonce_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas d'annonce avec l'id " + annonce_id));
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(Integer.parseInt(authorizationHeader));

        proposition.setAnnonce(annonce);
        proposition.setUtilisateur(utilisateur);

        return repository.save(proposition);
    }

    @PatchMapping("/proposition/{proposition_id}")
    Proposition postProposition(@RequestBody Proposition proposition, @PathVariable int annonce_id, @PathVariable int proposition_id, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        Annonce annonce = annonceRepository.findById(annonce_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas d'annonce avec l'id " + annonce_id));
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(Integer.parseInt(authorizationHeader));

        proposition.setAnnonce(annonce);
        proposition.setUtilisateur(utilisateur);

        return repository.save(proposition);
    }

    @GetMapping("/proposition/annonce/{annonce_id}")
    List<Proposition> getPropositionsAnnonce(@PathVariable int annonce_id) {
        return repository.findPropositionByAnnonce_Id(annonce_id);
    }

    @DeleteMapping("/proposition/{id}")
    boolean delete(@PathVariable(name = "id") Integer id) {

        try {
            repository.deleteById(id);
            return true;
        } catch (Error error) {
            return false;
        }
    }

}
