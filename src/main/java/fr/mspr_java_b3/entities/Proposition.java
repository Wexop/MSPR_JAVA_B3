package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Proposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime date;

    private String message;

    private EtatEnum etat;

    @ManyToOne
    @JoinColumn(name = "annonce_id")
    Annonce annonce;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    Utilisateur utilisateur;


    public Proposition() {
    }

    public Proposition(LocalDateTime date, String message, EtatEnum etat, Annonce annonce, Utilisateur utilisateur) {
        this.date = date;
        this.message = message;
        this.etat = etat;
        this.annonce = annonce;
        this.utilisateur = utilisateur;
    }

    public Proposition(int id, LocalDateTime date, String message, EtatEnum etat, Annonce annonce, Utilisateur utilisateur) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.etat = etat;
        this.annonce = annonce;
        this.utilisateur = utilisateur;
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

    public EtatEnum getEtat() {
        return etat;
    }

    public void setEtat(EtatEnum etat) {
        this.etat = etat;
    }
}
