package fr.mspr_java_b3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer article_id;
    private Integer utilisateur_id;
    private String message; // Stock Markdown
    private LocalDateTime date;
    private String image_url;

    public Commentaire() {
    }

    public Commentaire(Integer article_id, Integer utilisateur_id, String message, LocalDateTime date, String image_url) {
        this.article_id = article_id;
        this.utilisateur_id = utilisateur_id;
        this.message = message;
        this.date = date;
        this.image_url = image_url;
    }

    public Commentaire(int id, Integer article_id, Integer utilisateur_id, String message, LocalDateTime date, String image_url) {
        this.id = id;
        this.article_id = article_id;
        this.utilisateur_id = utilisateur_id;
        this.message = message;
        this.date = date;
        this.image_url = image_url;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Integer getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(Integer utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
