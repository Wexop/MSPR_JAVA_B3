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

    private PropositionEnum etat;

    @ManyToOne
    @JoinColumn(name = "annonce_id")
    Annonce annonce;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    Utilisateur utilisateur;


    public Proposition() {
    }

    public Proposition(LocalDateTime date, String message, PropositionEnum etat, Annonce annonce, Utilisateur utilisateur) {
        this.date = date;
        this.message = message;
        this.etat = etat;
        this.annonce = annonce;
        this.utilisateur = utilisateur;
    }

    public Proposition(int id, LocalDateTime date, String message, PropositionEnum etat, Annonce annonce, Utilisateur utilisateur) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.etat = etat;
        this.annonce = annonce;
        this.utilisateur = utilisateur;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }

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

    public PropositionEnum getEtat() {
        return etat;
    }

    public void setEtat(PropositionEnum etat) {
        this.etat = etat;
    }
}
