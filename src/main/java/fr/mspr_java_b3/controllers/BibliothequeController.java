package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.Bibliotheque;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.BibliothequeRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BibliothequeController {
    private final BibliothequeRepository repository;
    private final UtilisateurRepository utilisateurRepository;

    BibliothequeController(BibliothequeRepository repository, UtilisateurRepository utilisateurRepository) {
        this.repository = repository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/bibliotheque/me")
    List<Bibliotheque> getBibliotheque(@RequestHeader(value = "Utilisateur_id") String authorizationHeader) {

        return this.repository.getBibliothequeByUtilisateur(Integer.parseInt(authorizationHeader));

    }

    @PostMapping("/bibliotheque/one")
    Bibliotheque postArticle(@RequestBody Bibliotheque bibliotheque, @RequestHeader(value = "Utilisateur_id") String authorizationHeader) {

        Utilisateur utilisateur = utilisateurRepository.findById(Integer.parseInt(authorizationHeader))
                .orElseThrow(() -> new Error("Aucun utilisateur avec l'id " + authorizationHeader));

        bibliotheque.setUtilisateur(utilisateur);

        return repository.save(bibliotheque);
    }


}
