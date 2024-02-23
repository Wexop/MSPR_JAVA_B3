package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.controllers.requests_body.LoginUserRequest;
import fr.mspr_java_b3.controllers.responses.AuthResponse;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.AdresseRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import fr.mspr_java_b3.security.JwtUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UtilisateurController {
    private final UtilisateurRepository repository;
    private final AdresseRepository adresseRepository;

    UtilisateurController(UtilisateurRepository repository, AdresseRepository adresseRepository) {
        this.repository = repository;
        this.adresseRepository = adresseRepository;
    }

    @PostMapping("/login")
    AuthResponse loginUser(@RequestBody LoginUserRequest request) {

        Utilisateur utilisateur = this.repository.getUtilisateurByMail(request.getMail())
                .orElseThrow(() -> new Error("Aucun utilisateur trouvé avec le mail " + request.getMail()));

        if (!utilisateur.checkMdp(request.getMdp())) {
            throw new Error("Mot de passe incorrect");
        }

        JwtUtil jwtUtil = new JwtUtil();

        String token = jwtUtil.createToken(utilisateur);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);

        return authResponse;
    }

    @PostMapping("/register")
    AuthResponse loginUser(@RequestBody Utilisateur request) throws Exception {

        Optional<Utilisateur> utilisateur = this.repository.getUtilisateurByMail(request.getMail());

        if (utilisateur.isPresent()) {
            throw new Exception("Email déjà utilisé");
        }

        if (request.getAdresse() == null) {
            throw new Exception("Adresse invalide");
        }

        this.adresseRepository.save(request.getAdresse());

        JwtUtil jwtUtil = new JwtUtil();

        Utilisateur createdUser = this.repository.save(request);

        String token = jwtUtil.createToken(createdUser);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);


        return authResponse;
    }

    @GetMapping("/utilisateur/me")
    @SecurityRequirement(name = "bearer")
    Utilisateur getMe(@RequestAttribute(value = "Utilisateur_id") String authorizationHeader) throws Exception {
        return this.repository.findById(Integer.parseInt(authorizationHeader))
                .orElseThrow(() -> new Exception("Utilisteur introuvable"));
    }

    @DeleteMapping("/utilisateur/me")
    @SecurityRequirement(name = "bearer")
    Boolean deleteUser(@RequestAttribute(value = "Utilisateur_id") String authorizationHeader) throws Exception {

        try {
            this.repository.deleteById(Integer.parseInt(authorizationHeader));
            return true;
        } catch (Error e) {
            return false;
        }
    }


}
