package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.Article;
import fr.mspr_java_b3.repository.ArticleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArticleController {
    private final ArticleRepository repository;

    ArticleController(ArticleRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/article")
    List<Article> getAllArticle() {
        return repository.findAll().stream().toList();
    }

    @GetMapping("/article/{article_id}")
    Article getOneArticle(@PathVariable("article_id") Integer articleId) {
        return repository.findById(articleId)
                .orElseThrow(() -> new Error("Aucun utilisateur avec l'id " + articleId));
    }

    @PostMapping("/article")
    Article insertArticle(@RequestBody Article article) {
        return repository.save(article);
    }
}
