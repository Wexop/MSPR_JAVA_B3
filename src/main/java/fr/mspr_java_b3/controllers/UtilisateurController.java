package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.dto.*;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.AdresseRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import fr.mspr_java_b3.services.UtilisateurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UtilisateurController {
    private final UtilisateurRepository repository;
    private final AdresseRepository adresseRepository;
    private final UtilisateurService utilisateurService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    AuthDTO loginUser(@RequestBody LoginDTO request) {

        return utilisateurService.loginUser(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    AuthDTO registerUser(@RequestBody RegisterDTO request, HttpServletResponse response) throws Exception {

        return utilisateurService.registerUser(request, response);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/utilisateurs/me")
    @SecurityRequirement(name = "bearer")
    UtilisateurGetDTO getMe(@RequestAttribute(value = "Utilisateur_id") String authorizationHeader) throws Exception {
        return utilisateurService.getMe(authorizationHeader);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/utilisateurs/me")
    @SecurityRequirement(name = "bearer")
    Boolean deleteUser(@RequestAttribute(value = "Utilisateur_id") String authorizationHeader, @RequestBody DeleteUtilisateurDTO request) throws Exception {
        return utilisateurService.deleteUser(authorizationHeader, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "bearer")
    @PutMapping("/utilisateurs/me")
    Utilisateur putUtilisateur(@RequestBody PutUtilisateurDTO entity, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        return utilisateurService.putUtilisateur(entity, authorizationHeader);

    }

    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "bearer")
    @PutMapping("/utilisateurs/password")
    boolean putPassword(@RequestBody PutPasswordUserDTO request, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        return utilisateurService.putPassword(request, authorizationHeader);
    }

    @SecurityRequirement(name = "bearer")
    @PutMapping("/utilisateurs/mail")
    boolean putMail(@RequestBody PutMailUserDTO request, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        return utilisateurService.putMail(request, authorizationHeader);
    }


}
