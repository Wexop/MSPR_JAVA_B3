package fr.mspr_java_b3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer utilisateur_id;

    private String titre;
    private String message;
    private String image_url;

    public Article() {
    }

    public Article(Integer user_id, String titre, String message, String image_url) {
        this.utilisateur_id = user_id;
        this.titre = titre;
        this.message = message;
        this.image_url = image_url;
    }

    public Article(int id, Integer user_id, String titre, String message, String image_url) {
        this.id = id;
        this.utilisateur_id = user_id;
        this.titre = titre;
        this.message = message;
        this.image_url = image_url;
    }

    public Integer getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(Integer utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
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
