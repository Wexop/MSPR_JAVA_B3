package fr.mspr_java_b3.controllers.mapper;

import fr.mspr_java_b3.dto.ArticleDTO;
import fr.mspr_java_b3.entities.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public ArticleDTO toDTO(Article article) {
        if (article == null) {
            return null;
        }
        ArticleDTO dto = new ArticleDTO();
        dto.setTitre(article.getTitre());
        dto.setImage_url(article.getImage_url());
        dto.setContenue(article.getContenu());
        return dto;
    }

    public Article toArticle (ArticleDTO articleDTO) {
        if (articleDTO == null) {
            return null;
        }
        Article article = new Article();
        article.setTitre(articleDTO.getTitre());
        article.setImage_url(articleDTO.getImage_url());
        article.setContenu(articleDTO.getContenue());
        return article;
    }

    public ArticleDTO toGetDTO(Article article) {
        if (article == null) {
            return null;
        }
        ArticleDTO dto = new ArticleDTO();
        dto.setId(article.getId());
        dto.setTitre(article.getTitre());
        dto.setImage_url(article.getImage_url());
        dto.setContenue(article.getContenu());
        return dto;
    }

    public Article toGetArticle(ArticleDTO articleDTO) {
        if (articleDTO == null) {
            return null;
        }
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setTitre(articleDTO.getTitre());
        article.setImage_url(articleDTO.getImage_url());
        article.setContenu(articleDTO.getContenue());
        return article;
    }
}
