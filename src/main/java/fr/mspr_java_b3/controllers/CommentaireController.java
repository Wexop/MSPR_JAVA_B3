package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.Commentaire;
import fr.mspr_java_b3.repository.CommentaireRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@SecurityRequirement(name = "bearer")
public class CommentaireController {
    private final CommentaireRepository repository;

    CommentaireController(CommentaireRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/message")
    Commentaire insertCommentaire(@RequestBody Commentaire commentaire) {
        return repository.save(commentaire);
    }

    @GetMapping("/message/{id_article}")
    List<Commentaire> getAllCommentaireById(@PathVariable("article_id") Integer id_article) {
        return repository.findById(id_article).stream().toList();

    }

    @DeleteMapping("/message/{id}")
    boolean delete(@PathVariable(name = "id") Integer id) {

        try {
            repository.deleteById(id);
            return true;
        } catch (Error error) {
            return false;
        }
    }
}
