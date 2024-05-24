package fr.mspr_java_b3.controllers.mapper;

import fr.mspr_java_b3.dto.ArticlePostDTO;
import fr.mspr_java_b3.dto.ArticleGetDTO;
import fr.mspr_java_b3.entities.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public ArticlePostDTO toPostArtcileDTO(Article article) {
        if (article == null) {
            return null;
        }
        ArticlePostDTO dto = new ArticlePostDTO();
        dto.setTitre(article.getTitre());
        dto.setImage_url(article.getImage_url());
        dto.setContenue(article.getContenu());
        return dto;
    }

    public Article toPostArticle (ArticlePostDTO articleDTO) {
        if (articleDTO == null) {
            return null;
        }
        Article article = new Article();
        article.setTitre(articleDTO.getTitre());
        article.setImage_url(articleDTO.getImage_url());
        article.setContenu(articleDTO.getContenue());
        return article;
    }

    public ArticleGetDTO toArticleGetDTO(Article article) {
        if (article == null) {
            return null;
        }
        ArticleGetDTO dto = new ArticleGetDTO();
        dto.setId(article.getId());
        dto.setTitre(article.getTitre());
        dto.setImage_url(article.getImage_url());
        dto.setContenue(article.getContenu());
        return dto;
    }

    public Article toGetArticle(ArticleGetDTO articleGetDTO) {
        if (articleGetDTO == null) {
            return null;
        }
        Article article = new Article();
        article.setId(articleGetDTO.getId());
        article.setTitre(articleGetDTO.getTitre());
        article.setImage_url(articleGetDTO.getImage_url());
        article.setContenu(articleGetDTO.getContenue());
        return article;
    }
}
