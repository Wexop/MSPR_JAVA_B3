package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.controllers.requests_body.PutArticleRequest;
import fr.mspr_java_b3.entities.Article;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.ArticleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ArticleController {
    private final ArticleRepository repository;

    ArticleController(ArticleRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/article/all")
    List<Article> getAllArticle() {
        return repository.findAll();
    }

    @GetMapping("/article_by_id/{id}")
    Article getOneArticle(@PathVariable("id") String articleId) {
        return repository.findById(Integer.parseInt(articleId))
                .orElseThrow(() -> new Error("Aucun article avec l'id " + articleId));
    }

    @PostMapping("/article/one")
    Article postArticle(@RequestBody Article article, @RequestHeader(value = "Utilisateur_id") String authorizationHeader) {

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(Integer.parseInt(authorizationHeader));

        article.setUtilisateur(utilisateur);

        return repository.save(article);
    }

    @PatchMapping("/article_by_id/{id}")
    Article patchArticle(@RequestBody Article article, @PathVariable int id) {

        Article articleFound = repository.findById(id).orElseThrow(() -> new Error("Aucun article avec l'id " + id));

        return repository.save(article);
    }

    @DeleteMapping("/article/{id}")
    boolean delete(@PathVariable(name = "id") Integer id) {

        try {
            repository.deleteById(id);
            return true;
        } catch (Error error) {
            return false;
        }
    }

    @PutMapping("/article/{id}")
    Article putAnnonce(@RequestBody PutArticleRequest entity, @PathVariable(name = "id") Integer id) {

        Article initialEntity = repository.getReferenceById(id);

        initialEntity.setTitre(entity.titre);
        initialEntity.setContenu(entity.contenu);

        return repository.save(initialEntity);

    }

}
