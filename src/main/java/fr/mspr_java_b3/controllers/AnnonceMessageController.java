package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.dto.AnnonceMessageGetDTO;
import fr.mspr_java_b3.dto.AnnonceMessagePostDTO;
import fr.mspr_java_b3.services.AnnonceMessageService;
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
public class AnnonceMessageController {
    private final AnnonceMessageService annonceMessageService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages/annonces/{id_annonce}")
    List<AnnonceMessageGetDTO> getAllMessageByAnnonce(@PathVariable int id_annonce) {
        return annonceMessageService.getAllMessageByAnnonce(id_annonce);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/messages/annonces/{id_annonce}")
    AnnonceMessageGetDTO postMessageAnnonce(@PathVariable int id_annonce, @RequestBody AnnonceMessagePostDTO annonce, @RequestAttribute(value = "Utilisateur_id") String authorizationValue) {
        return annonceMessageService.postMessageAnnonce(id_annonce, annonce, authorizationValue);
    }
}
