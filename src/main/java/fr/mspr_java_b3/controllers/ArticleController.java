package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.Article;
import fr.mspr_java_b3.repository.ArticleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    ResponseEntity<Article> getArticle(@PathVariable("article_id") Integer articleId) {
        Optional<Article> articleOptional = repository.findById(articleId);

        if (articleOptional.isPresent()) {
            return ResponseEntity.ok(articleOptional.get());
        } else {
            return ResponseEntity.notFound().build(); // Erreur 404
        }
    }

    public ResponseEntity<Article> insertArticle(@RequestBody Article article) {
        Article savedArticle = repository.save(article);
        return ResponseEntity.ok(savedArticle);
    }
}
