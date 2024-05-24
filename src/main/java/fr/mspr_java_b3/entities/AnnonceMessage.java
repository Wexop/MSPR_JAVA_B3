package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AnnonceMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String image_url;

    private String message;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "annonce_id")
    Annonce annonce;

    public AnnonceMessage() {
    }

    public AnnonceMessage(String image_url, String message, LocalDateTime date, Utilisateur utilisateur, Annonce annonce) {
        this.image_url = image_url;
        this.message = message;
        this.date = date;
        this.utilisateur = utilisateur;
        this.annonce = annonce;
    }

    public AnnonceMessage(int id, String image_url, String message, LocalDateTime date, Utilisateur utilisateur, Annonce annonce) {
        this.id = id;
        this.image_url = image_url;
        this.message = message;
        this.date = date;
        this.utilisateur = utilisateur;
        this.annonce = annonce;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
