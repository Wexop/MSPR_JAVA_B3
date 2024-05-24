package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.dto.PropositionGetDTO;
import fr.mspr_java_b3.dto.PropositionPatchDTO;
import fr.mspr_java_b3.dto.PropositionPostDTO;
import fr.mspr_java_b3.entities.Proposition;
import fr.mspr_java_b3.services.PropositionService;
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
public class PropositionController {
    private final PropositionService propositionService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("proposition/{id}")
    PropositionGetDTO getProposition(@PathVariable int id) {
        return propositionService.getProposition(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/proposition/annonce/{annonce_id}")
    List<PropositionGetDTO> getPropositionsByAnnonce(@PathVariable int annonce_id) {
        return propositionService.getPropositionsByAnnonce(annonce_id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("proposition/annonce/{annonce_id}")
    PropositionGetDTO postProposition(@RequestBody PropositionPostDTO proposition, @PathVariable int annonce_id, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {
        return propositionService.postProposition(proposition, annonce_id, authorizationHeader);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/proposition/{proposition_id}")
    PropositionGetDTO patchProposition(@RequestBody PropositionPatchDTO proposition, @PathVariable int annonce_id, @PathVariable int proposition_id, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {
        return propositionService.patchProposition(proposition, annonce_id, proposition_id, authorizationHeader);
    }
}
