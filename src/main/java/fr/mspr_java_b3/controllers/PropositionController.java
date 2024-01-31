package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.Proposition;
import fr.mspr_java_b3.repository.PropositionRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class PropositionController {

    private final PropositionRepository repository;

    PropositionController(PropositionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/proposition/{id}")
    Proposition getProposition(@PathVariable int id) {
        return repository.findById(id).orElseThrow(() -> new Error("Pas de proposition avec l'id " + id));
    }

    @PostMapping("/proposition")
    Proposition postProposition(@RequestBody Proposition proposition) {
        return repository.save(proposition);
    }

    @PatchMapping("/proposition")
    Proposition patchProposition(@RequestBody Proposition proposition) {
        return repository.save(proposition);
    }

}