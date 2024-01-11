package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reponse_annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer annonce_id;

    private Integer utilisateur_id;

    private LocalDateTime date;

    private String message;

    private String image_url;

    public Reponse_annonce(Integer annonce_id, Integer utilisateur_id, LocalDateTime date, String message, String image_url) {
        this.annonce_id = annonce_id;
        this.utilisateur_id = utilisateur_id;
        this.date = date;
        this.message = message;
        this.image_url = image_url;
    }

    public Reponse_annonce(int id, Integer annonce_id, Integer utilisateur_id, LocalDateTime date, String message, String image_url) {
        this.id = id;
        this.annonce_id = annonce_id;
        this.utilisateur_id = utilisateur_id;
        this.date = date;
        this.message = message;
        this.image_url = image_url;
    }

    public Reponse_annonce() {
    }

    public Integer getAnnonce_id() {
        return annonce_id;
    }

    public void setAnnonce_id(Integer annonce_id) {
        this.annonce_id = annonce_id;
    }

    public Integer getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(Integer utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
