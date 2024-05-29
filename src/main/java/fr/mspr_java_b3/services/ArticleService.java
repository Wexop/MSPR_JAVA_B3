package fr.mspr_java_b3.services;

import fr.mspr_java_b3.controllers.mapper.ArticleMapper;
import fr.mspr_java_b3.dto.ArticleGetDTO;
import fr.mspr_java_b3.dto.ArticlePostDTO;
import fr.mspr_java_b3.entities.Article;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.ArticleRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final UtilisateurRepository utilisateurRepository;

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

    public ArticleGetDTO postArticle(ArticlePostDTO dto, String authorizationHeader) {
        Article article = articleMapper.toPostArticle(dto);
        article.setUtilisateur(utilisateurRepository.getReferenceById(Integer.parseInt(authorizationHeader)));
        article = articleRepository.save(article);
        return articleMapper.toArticleGetDTO(article);
    }

    public ArticleGetDTO patchArticle(ArticlePostDTO dto, Integer id) {
        Article originalArticle = this.articleRepository.getReferenceById(id);

        Article entity = articleMapper.toPatchArticle(dto, originalArticle);
        entity.setId(id);
        Article saved = articleRepository.save(entity);
        return articleMapper.toArticleGetDTO(saved);
    }
}
