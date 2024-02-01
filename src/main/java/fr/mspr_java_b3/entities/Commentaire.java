package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String message; // Stock Markdown
    private LocalDateTime date;
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "article_id")
    Article article;

    public Commentaire() {
    }

    public Commentaire(String message, LocalDateTime date, String image_url, Utilisateur utilisateur, Article article) {
        this.message = message;
        this.date = date;
        this.image_url = image_url;
        this.utilisateur = utilisateur;
        this.article = article;
    }

    public Commentaire(int id, String message, LocalDateTime date, String image_url, Utilisateur utilisateur, Article article) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.image_url = image_url;
        this.utilisateur = utilisateur;
        this.article = article;
    }

    public int getId() {
        return id;
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
