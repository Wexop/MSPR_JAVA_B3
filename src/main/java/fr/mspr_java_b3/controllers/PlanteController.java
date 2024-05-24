package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.dto.PlanteGetDTO;
import fr.mspr_java_b3.dto.PlantePostDTO;
import fr.mspr_java_b3.services.PlanteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer")
public class PlanteController {
    private final PlanteService planteService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/plantes/me")
    List<PlanteGetDTO> getMesPlantes(@RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {
        return planteService.getMesPlantes(authorizationHeader);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/plante")
    PlanteGetDTO postPlante(@RequestBody PlanteGetDTO body, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {
        return planteService.postPlante(body, authorizationHeader);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/plante/{id}")
    PlanteGetDTO patchPlante(@RequestBody PlantePostDTO entity, @PathVariable("id") Integer id) {
        return planteService.patchPlante(entity, id);
    }
}
