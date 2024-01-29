package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.AnnonceMessage;
import fr.mspr_java_b3.repository.AnnonceMessageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnnonceMessageController {

    private final AnnonceMessageRepository repository;

    AnnonceMessageController(AnnonceMessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/message/annonce/{id}")
    List<AnnonceMessage> getMessageAnnonce(@PathVariable int id) {
        return repository.findByAnnonce(id);
    }
}
