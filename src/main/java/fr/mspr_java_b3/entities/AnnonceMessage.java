package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AnnonceMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer annonce_id;

    private String image_url;

    private String message;

    private Integer personne_id;

    private LocalDateTime date;

    public AnnonceMessage(int id, Integer annonce_id, String image_url, String message, Integer personne_id, LocalDateTime date) {
        this.id = id;
        this.annonce_id = annonce_id;
        this.image_url = image_url;
        this.message = message;
        this.personne_id = personne_id;
        this.date = date;
    }

    public AnnonceMessage(Integer annonce_id, String image_url, String message, Integer personne_id, LocalDateTime date) {
        this.annonce_id = annonce_id;
        this.image_url = image_url;
        this.message = message;
        this.personne_id = personne_id;
        this.date = date;
    }

    public AnnonceMessage() {
    }

    public Integer getAnnonce_id() {
        return annonce_id;
    }

    public void setAnnonce_id(Integer annonce_id) {
        this.annonce_id = annonce_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Integer getPersonne_id() { return personne_id; }

    public void setPersonne_id(Integer personne_id) { this.personne_id = personne_id; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }
}
