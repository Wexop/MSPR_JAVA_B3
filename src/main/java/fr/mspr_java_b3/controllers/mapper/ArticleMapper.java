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
        dto.setContenue(article.getContenu());
        return dto;
    }

    public Article toPostArticle (ArticlePostDTO articleDTO) {
        if (articleDTO == null) {
            return null;
        }
        Article article = new Article();
        article.setTitre(articleDTO.getTitre());
        article.setContenu(articleDTO.getContenue());
        return article;
    }

    public Article toPatchArticle (ArticlePostDTO dto, Article article) {
        if (dto == null) {
            return null;
        }

        if (dto.getTitre() != null) article.setTitre(dto.getTitre());
        if (dto.getContenue() != null) article.setContenu(dto.getContenue());
        return article;
    }

    public ArticleGetDTO toArticleGetDTO(Article article) {
        if (article == null) {
            return null;
        }
        ArticleGetDTO dto = new ArticleGetDTO();
        dto.setId(article.getId());
        dto.setContenue(article.getContenu());
        dto.setTitre(article.getTitre());
        dto.setDate(article.getDate());
        dto.setUtilisateur(new UtilisateurMapper().toUtilisateurGetDTO(article.getUtilisateur()));
        return dto;
    }

    public Article toGetArticle(ArticleGetDTO articleGetDTO) {
        if (articleGetDTO == null) {
            return null;
        }
        Article article = new Article();
        article.setId(articleGetDTO.getId());
        article.setContenu(articleGetDTO.getContenue());
        article.setTitre(articleGetDTO.getTitre());
        article.setDate(articleGetDTO.getDate());
        return article;
    }
}
