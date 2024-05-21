package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.dto.*;
import fr.mspr_java_b3.services.AnnonceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@SecurityRequirement(name = "bearer")
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnnonceController {
    private final AnnonceService annonceService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/annonce/{id}")
    AnnonceGetDTO getAnnonceById(@PathVariable int id) {
        return annonceService.getAnnonceById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/annonces_attente")
    List<AnnonceGetDTO> getAnnonceAttente() {
        return annonceService.getAnnonceAttente();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/annonces_aide")
    List<AnnonceGetDTO> getAnnonceAide() {
        return annonceService.getAnnonceAide();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/annonces/me")
    List<AnnonceGetDTO> getMesAnnonces(@RequestAttribute(value = "Utilisateur_id") String authorizationValue) {
        return annonceService.getMesAnnonces(authorizationValue);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/annonces_garde/me")
    List<AnnonceGetDTO> getMesGardes(@RequestAttribute(value = "Utilisateur_id") String authorizationValue) {
        return annonceService.getMesGardes(authorizationValue);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/annonce")
    AnnonceGetDTO postAnnonce(@RequestBody AnnonceGetDTO body) {
        return annonceService.postAnnonce(body);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/annonce/{id}")
    AnnonceGetDTO putAnnonce(@RequestBody AnnonceGetDTO entity, @PathVariable(name = "id") Integer id) {
        return annonceService.putAnnonce(entity, id);
    }
}
