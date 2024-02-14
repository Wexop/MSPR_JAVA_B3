package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.controllers.requests_body.LoginUserRequest;
import fr.mspr_java_b3.entities.Commentaire;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.CommentaireRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class UtilisateurController {
    private final UtilisateurRepository repository;

    UtilisateurController(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/login")
    Utilisateur loginUser(@RequestBody LoginUserRequest request) {

        Utilisateur utilisateur = this.repository.getUtilisateurByMail(request.getMail())
                .orElseThrow(() -> new Error("Aucun utilisateur trouvé avec le mail " + request.getMail()));

        if(!Objects.equals(utilisateur.getMdp(), request.getMdp())){
            throw new Error("Mot de passe incorrect");
        }

        return utilisateur;
    }

    @PostMapping("/register")
    Utilisateur loginUser(@RequestBody Utilisateur request) {

        return this.repository.save(request);
    }


}
