package fr.mspr_java_b3.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Proposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
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

    public Proposition(LocalDateTime date, String message, PropositionEnum etat, Annonce annonce) {
        this.date = date;
        this.message = message;
        this.etat = etat;
        this.annonce = annonce;
    }

    public Proposition(int id, LocalDateTime date, String message, PropositionEnum etat, Annonce annonce) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.etat = etat;
        this.annonce = annonce;
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
