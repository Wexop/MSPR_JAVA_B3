package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.Annonce;
import fr.mspr_java_b3.entities.AnnonceEnum;
import fr.mspr_java_b3.repository.AnnonceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnnonceController {

    private final AnnonceRepository repository;

    AnnonceController(AnnonceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/annonce")
    List<Annonce> getAnnonce() {
        return repository.findByEtat(AnnonceEnum.en_cours);
    }

    @GetMapping("/annonce_aide")
    List<Annonce> getAnnonceAide() {
        return repository.findNeedHelp(AnnonceEnum.en_cours);
    }

    @GetMapping("/annonce/{id}")
    Annonce one(@PathVariable int id) {

        return repository.findById(id)
                .orElseThrow(() -> new Error("Aucun utilisateur avec l'id " + id));
    }

    @GetMapping("/mes_annonces/{id}")
    List<Annonce> mesAnnonce(@PathVariable int id) {


        return repository.findByUser(id);
    }

    @PostMapping("/annonce")
    Annonce postAnnonce(@RequestBody Annonce body) {
        return repository.save(body);
    }

}
