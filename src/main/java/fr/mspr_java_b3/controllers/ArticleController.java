package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.dto.ArticleGetDTO;
import fr.mspr_java_b3.dto.ArticlePostDTO;
import fr.mspr_java_b3.services.ArticleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/articles")
    List<ArticleGetDTO> getAllArticles() {
        return articleService.getAllArticles();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/articles/{id}")
    ArticleGetDTO getArticleById(@PathVariable("id") String articleId) {
        return articleService.getArticleById(articleId);
    }

    @SecurityRequirement(name = "bearer")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/articles")
    ArticleGetDTO postArticle(@RequestBody ArticlePostDTO article, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {
        return articleService.postArticle(article, authorizationHeader);
    }

    @SecurityRequirement(name = "bearer")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/articles/{id}")
    ArticleGetDTO patchArticle(@RequestBody ArticlePostDTO article, @PathVariable(name = "id") Integer id) {
        return articleService.patchArticle(article, id);
    }
}
