package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.controllers.requests_body.LoginUserRequest;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "localhost:3000")
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

        if (!Objects.equals(utilisateur.getMdp(), request.getMdp())) {
            throw new Error("Mot de passe incorrect");
        }

        return utilisateur;
    }

    @PostMapping("/register")
    Utilisateur loginUser(@RequestBody Utilisateur request) throws Error {

        Optional<Utilisateur> utilisateur = this.repository.getUtilisateurByMail(request.getMail());

        if (utilisateur.isPresent()) {
            throw new Error("Email déjà utilisé");
        }

        return this.repository.save(request);
    }


}
