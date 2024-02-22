package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.AnnonceMessage;
import fr.mspr_java_b3.repository.AnnonceMessageRepository;
import fr.mspr_java_b3.repository.AnnonceRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@SecurityRequirement(name = "bearer")
public class AnnonceMessageController {

    private final AnnonceMessageRepository repository;
    private final AnnonceRepository annonceRepository;

    AnnonceMessageController(AnnonceMessageRepository repository, AnnonceRepository annonceRepository) {
        this.repository = repository;
        this.annonceRepository = annonceRepository;
    }

    @GetMapping("/message/annonce/{id}")
    List<AnnonceMessage> getMessageAnnonce(@PathVariable int id) {
        return repository.findByAnnonce(id);
    }

    @PostMapping("/message/annonce/{id}")
    AnnonceMessage postMessageAnnonce(@PathVariable int id, @RequestBody AnnonceMessage annonce) {
        var annonceFound = annonceRepository.findById(id).orElseThrow(() -> new Error("Aucune annonce avec l'id " + id));
        if (annonceFound != null) {
            annonce.setAnnonce(annonceFound);
        }
        return repository.save(annonce);
    }

    @DeleteMapping("/message/annonce/{id}")
    boolean delete(@PathVariable(name = "id") Integer id) {

        try {
            repository.deleteById(id);
            return true;
        } catch (Error error) {
            return false;
        }
    }
}
