package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.Bibliotheque;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.BibliothequeRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearer")
public class BibliothequeControllerAChanger {
    private final BibliothequeRepository repository;
    private final UtilisateurRepository utilisateurRepository;

    BibliothequeControllerAChanger(BibliothequeRepository repository, UtilisateurRepository utilisateurRepository) {
        this.repository = repository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/bibliotheque/me")
    List<Bibliotheque> getBibliotheque(@RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        return this.repository.getBibliothequeByUtilisateur(Integer.parseInt(authorizationHeader));

    }

    @PostMapping("/bibliotheque/one")
    Bibliotheque postArticle(@RequestBody Bibliotheque bibliotheque, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        Utilisateur utilisateur = utilisateurRepository.findById(Integer.parseInt(authorizationHeader))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de d'utilisateur avec l'id " + authorizationHeader));

        bibliotheque.setUtilisateur(utilisateur);

        return repository.save(bibliotheque);
    }


}
