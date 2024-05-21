package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.controllers.requests_body.PutArticleRequest;
import fr.mspr_java_b3.entities.Article;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.ArticleRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleControllerAChanger {
    private final ArticleRepository repository;

    ArticleControllerAChanger(ArticleRepository repository) {
        this.repository = repository;
    }

    @SecurityRequirement(name = "")
    @GetMapping("/article/all")
    List<Article> getAllArticle() {
        return repository.findAll();
    }

    @GetMapping("/article_by_id/{id}")
    Article getOneArticle(@PathVariable("id") String articleId) {
        return repository.findById(Integer.parseInt(articleId))
                .orElseThrow(() -> new Error("Aucun article avec l'id " + articleId));
    }

    @SecurityRequirement(name = "bearer")
    @PostMapping("/article/one")
    Article postArticle(@RequestBody Article article, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(Integer.parseInt(authorizationHeader));

        article.setUtilisateur(utilisateur);

        return repository.save(article);
    }

    @SecurityRequirement(name = "bearer")
    @DeleteMapping("/article/{id}")
    boolean delete(@PathVariable(name = "id") Integer id) {

        try {
            repository.deleteById(id);
            return true;
        } catch (Error error) {
            return false;
        }
    }

    @SecurityRequirement(name = "bearer")
    @PutMapping("/article/{id}")
    Article putAnnonce(@RequestBody PutArticleRequest entity, @PathVariable(name = "id") Integer id) {

        Article initialEntity = repository.getReferenceById(id);

        if(entity.titre != null) initialEntity.setTitre(entity.titre);
        if(entity.contenu != null) initialEntity.setContenu(entity.contenu);

        return repository.save(initialEntity);

    }

}
