package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer utilisateur_id;

    private Integer plante_id;

    private String titre;

    private String description;

    private LocalDateTime date;

    private EtatEnum etat;

    public Annonce(int id, Integer utilisateur_id, Integer plante_id, String titre, String description, LocalDateTime date, EtatEnum etat) {
        this.id = id;
        this.utilisateur_id = utilisateur_id;
        this.plante_id = plante_id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.etat = etat;
    }

    public Annonce(Integer utilisateur_id, Integer plante_id, String titre, String description, LocalDateTime date, EtatEnum etat) {
        this.utilisateur_id = utilisateur_id;
        this.plante_id = plante_id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.etat = etat;
    }

    public Annonce() {
    }

    public Integer getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(Integer utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public Integer getPlante_id() {
        return plante_id;
    }

    public void setPlante_id(Integer plante_id) {
        this.plante_id = plante_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public EtatEnum getEtat() {
        return etat;
    }

    public void setEtat(EtatEnum etat) {
        this.etat = etat;
    }
}
