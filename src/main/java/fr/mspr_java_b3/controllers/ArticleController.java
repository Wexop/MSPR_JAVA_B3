package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.dto.ArticleGetDTO;
import fr.mspr_java_b3.services.ArticleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/articles")
    List<ArticleGetDTO> getAllArticles() {
        return articleService.getAllArticles();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/article/{id}")
    ArticleGetDTO getArticleById(@PathVariable("id") String articleId) {
        return articleService.getArticleById(articleId);
    }

    @SecurityRequirement(name = "bearer")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/article")
    ArticleGetDTO postArticle(@RequestBody ArticleGetDTO article, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {
        return articleService.postArticle(article, authorizationHeader);
    }

    @SecurityRequirement(name = "bearer")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/article/{id}")
    ArticleGetDTO putArticle(@RequestBody ArticleGetDTO article, @PathVariable(name = "id") Integer id) {
        return articleService.putArticle(article, id);
    }
}
