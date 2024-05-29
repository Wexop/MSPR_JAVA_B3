package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.dto.AnnonceGetDTO;
import fr.mspr_java_b3.dto.AnnoncePostDTO;
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
@RequiredArgsConstructor
public class AnnonceController {
    private final AnnonceService annonceService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/annonces/{id}")
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/annonces_attente/me")
    List<AnnonceGetDTO> getMesAnnoncesAttente(@RequestAttribute(value = "Utilisateur_id") String authorizationValue) {
        return annonceService.getMesAnnoncesAttente(authorizationValue);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/annonces")
    AnnonceGetDTO postAnnonce(@RequestBody AnnoncePostDTO body,@RequestAttribute(value = "Utilisateur_id")String authorizationValue) {
        return annonceService.postAnnonce(body, Integer.parseInt(authorizationValue));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/annonces/{id}")
    AnnonceGetDTO patchAnnonce(@RequestBody AnnoncePostDTO entity, @PathVariable(name = "id") Integer id) {
        return annonceService.patchAnnonce(entity, id);
    }
}