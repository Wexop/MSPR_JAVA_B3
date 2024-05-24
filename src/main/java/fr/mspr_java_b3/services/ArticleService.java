package fr.mspr_java_b3.services;

import fr.mspr_java_b3.controllers.mapper.ArticleMapper;
import fr.mspr_java_b3.dto.ArticleGetDTO;
import fr.mspr_java_b3.entities.Article;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public List<ArticleGetDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(articleMapper::toArticleGetDTO)
                .collect(Collectors.toList());
    }

    public ArticleGetDTO getArticleById(String articleId) {
        return articleRepository.findById(Integer.parseInt(articleId))
                .map(articleMapper::toArticleGetDTO)
                .orElseThrow(() -> new Error("Aucun article avec l'id" + articleId));
    }

    public ArticleGetDTO postArticle(ArticleGetDTO dto, String authorizationHeader) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(Integer.parseInt(authorizationHeader));

        Article article = articleMapper.toGetArticle(dto);
        article.setUtilisateur(utilisateur);
        article = articleRepository.save(article);
        return articleMapper.toArticleGetDTO(article);
    }

    public ArticleGetDTO putArticle(ArticleGetDTO dto, Integer id) {
        dto.setId(id);
        Article entity = articleMapper.toGetArticle(dto);
        Article saved = articleRepository.save(entity);
        return articleMapper.toArticleGetDTO(saved);
    }
}
