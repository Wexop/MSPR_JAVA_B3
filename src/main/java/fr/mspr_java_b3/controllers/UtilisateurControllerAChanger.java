package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.controllers.requests_body.*;
import fr.mspr_java_b3.controllers.responses.AuthResponse;
import fr.mspr_java_b3.entities.Adresse;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.AdresseRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import fr.mspr_java_b3.security.JwtUtil;
import fr.mspr_java_b3.services.UtilisateurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class UtilisateurControllerAChanger {
    private final UtilisateurRepository repository;
    private final AdresseRepository adresseRepository;
    private final UtilisateurService utilisateurService = new UtilisateurService();

    UtilisateurControllerAChanger(UtilisateurRepository repository, AdresseRepository adresseRepository) {
        this.repository = repository;
        this.adresseRepository = adresseRepository;
    }

    @PostMapping("/login")
    AuthResponse loginUser(@RequestBody LoginUserRequest request) {

        Utilisateur utilisateur = this.repository.getUtilisateurByMail(request.getMail())
                .orElseThrow(() -> new Error("Aucun utilisateur trouvé avec le mail " + request.getMail()));

        if (!utilisateurService.checkMdp(utilisateur, request.getMdp())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrect");
        }

        JwtUtil jwtUtil = new JwtUtil();

        String token = jwtUtil.createToken(utilisateur);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUserId(utilisateur.getId());

        return authResponse;
    }

    @PostMapping("/register")
    AuthResponse loginUser(@RequestBody Utilisateur request, HttpServletResponse response) throws Exception {

        Optional<Utilisateur> utilisateur = this.repository.getUtilisateurByMail(request.getMail());

        if (utilisateur.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email déjà utilisé");
        }

        if (request.getAdresse() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adresse invalide");
        }

        this.adresseRepository.save(request.getAdresse());

        JwtUtil jwtUtil = new JwtUtil();

        Utilisateur userWithHash = utilisateurService.hashMdp(request);

        Utilisateur createdUser = this.repository.save(userWithHash);

        String token = jwtUtil.createToken(createdUser);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUserId(createdUser.getId());


        return authResponse;
    }

    @GetMapping("/utilisateur/me")
    @SecurityRequirement(name = "bearer")
    Utilisateur getMe(@RequestAttribute(value = "Utilisateur_id") String authorizationHeader) throws Exception {
        return this.repository.findById(Integer.parseInt(authorizationHeader))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisteur introuvable"));
    }

    @DeleteMapping("/utilisateur/me")
    @SecurityRequirement(name = "bearer")
    Boolean deleteUser(@RequestAttribute(value = "Utilisateur_id") String authorizationHeader, @RequestBody DeleteUserRequest request) throws Exception {

        try {
            Utilisateur utilisateur = this.repository.findById(Integer.parseInt(authorizationHeader))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id " + Integer.parseInt(authorizationHeader)));

            if (!utilisateurService.checkMdp(utilisateur, request.getMdp())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrect");
            }
            this.repository.deleteById(Integer.parseInt(authorizationHeader));
            return true;
        } catch (Error e) {
            return false;
        }
    }

    @SecurityRequirement(name = "bearer")
    @PutMapping("/utilisateur/me")
    Utilisateur putUtilisateur(@RequestBody PutUtilisateurRequest entity, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        Utilisateur initialEntity = repository.getReferenceById(Integer.parseInt(authorizationHeader));

        if (entity.nom != null) initialEntity.setNom(entity.nom);
        if (entity.image_url != null) initialEntity.setImage_url(entity.image_url);
        if (entity.botaniste != null) initialEntity.setBotaniste(entity.botaniste);
        if (entity.adresse != null) {
            Adresse adresse = adresseRepository.findById(initialEntity.getAdresse().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune adresse trouvée avec l'id " + initialEntity.getAdresse().getId()));
            Adresse adresseEntity = new Adresse(adresse.getId(), entity.adresse.getAdresse(), entity.adresse.getLatitude(), entity.adresse.getLongitude());
            initialEntity.setAdresse(adresseEntity);
            adresseRepository.save(adresseEntity);
        }
        return repository.save(initialEntity);

    }

    @SecurityRequirement(name = "bearer")
    @PutMapping("/utilisateur/password")
    boolean putPassword(@RequestBody PutPasswordUserRequest request, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        Utilisateur utilisateur = this.repository.findById(Integer.parseInt(authorizationHeader))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id " + Integer.parseInt(authorizationHeader)));

        if (!utilisateurService.checkMdp(utilisateur, request.getMdp())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrect");
        }

        utilisateur.setMdp(request.getNewMdp());

        this.repository.save(utilisateur);

        return true;
    }

    @SecurityRequirement(name = "bearer")
    @PutMapping("/utilisateur/mail")
    boolean putMail(@RequestBody PutMailUserRequest request, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        Utilisateur utilisateur = this.repository.findById(Integer.parseInt(authorizationHeader))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id " + Integer.parseInt(authorizationHeader)));

        if (!utilisateurService.checkMdp(utilisateur, request.getMdp())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrect");
        }

        utilisateur.setMail(request.getNewMail());

        this.repository.save(utilisateur);

        return true;
    }


}
