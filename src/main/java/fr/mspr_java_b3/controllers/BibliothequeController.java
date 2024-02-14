package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.Bibliotheque;
import fr.mspr_java_b3.repository.BibliothequeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BibliothequeController {
    private final BibliothequeRepository repository;

    BibliothequeController(BibliothequeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/bibliotheque/me")
    List<Bibliotheque> getBibliotheque(@RequestHeader(value = "Utilisateur_id") String authorizationHeader) {

        return this.repository.getBibliothequeByUtilisateur(Integer.parseInt(authorizationHeader));

    }

}
