package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.Annonce;
import fr.mspr_java_b3.entities.AnnonceEnum;
import fr.mspr_java_b3.repository.AnnonceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnnonceController {

    private final AnnonceRepository repository;

    AnnonceController(AnnonceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/annonce")
    List<Annonce> getAnnonce() {
        return repository.findAll().stream().filter(annonce -> annonce.getEtat() == AnnonceEnum.en_attente).toList();
    }

    @GetMapping("/annonce/{id}")
    Annonce one(@PathVariable int id) {

        return repository.findById(id)
                .orElseThrow(() -> new Error("Aucun utilisateur avec l'id " + id));
    }

}
