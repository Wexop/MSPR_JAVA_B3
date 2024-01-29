package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;
    private String message;
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    Utilisateur utilisateur;

    @OneToMany(mappedBy = "article")
    Set<Commentaire> commentaires;

    public Article() {
    }

    public Article(String titre, String message, String image_url, Utilisateur utilisateur) {
        this.titre = titre;
        this.message = message;
        this.image_url = image_url;
        this.utilisateur = utilisateur;
    }

    public Article(int id, String titre, String message, String image_url, Utilisateur utilisateur) {
        this.id = id;
        this.titre = titre;
        this.message = message;
        this.image_url = image_url;
        this.utilisateur = utilisateur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
