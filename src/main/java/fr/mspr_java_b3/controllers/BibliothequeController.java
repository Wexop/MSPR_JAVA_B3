package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.dto.BibliothequeGetDTO;
import fr.mspr_java_b3.dto.BibliothequePostDTO;
import fr.mspr_java_b3.services.BibliothequeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@SecurityRequirement(name = "bearer")
@RequiredArgsConstructor
public class BibliothequeController {
    private final BibliothequeService bibliothequeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/bibliotheque/me")
    List<BibliothequeGetDTO> getMesBibliotheque(@RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {
        return bibliothequeService.getMesBibliotheque(authorizationHeader);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/bibliotheque")
    BibliothequeGetDTO postBibliotheque(@RequestBody BibliothequePostDTO bibliotheque, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {
        return bibliothequeService.postBibliotheque(bibliotheque, authorizationHeader);
    }
}
