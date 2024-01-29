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

    private LocalDateTime date_creation;

    private EtatEnum etat;

    private LocalDateTime date_debut;

    private LocalDateTime date_fin;

    private Boolean besoin_aide;

    public Annonce(int id, Integer utilisateur_id, Integer plante_id, String titre, String description, LocalDateTime date_creation, EtatEnum etat, LocalDateTime date_debut, LocalDateTime date_fin, Boolean besoin_aide) {
        this.id = id;
        this.utilisateur_id = utilisateur_id;
        this.plante_id = plante_id;
        this.titre = titre;
        this.description = description;
        this.date_creation = date_creation;
        this.etat = etat;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.besoin_aide = besoin_aide;
    }

    public Annonce(Integer utilisateur_id, Integer plante_id, String titre, String description, LocalDateTime date_creation, EtatEnum etat, LocalDateTime date_debut, LocalDateTime date_fin, Boolean besoin_aide) {
        this.utilisateur_id = utilisateur_id;
        this.plante_id = plante_id;
        this.titre = titre;
        this.description = description;
        this.date_creation = date_creation;
        this.etat = etat;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.besoin_aide = besoin_aide;
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

    public LocalDateTime getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDateTime date_creation) {
        this.date_creation = date_creation;
    }

    public EtatEnum getEtat() {
        return etat;
    }

    public void setEtat(EtatEnum etat) {
        this.etat = etat;
    }

    public LocalDateTime getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDateTime date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDateTime getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDateTime date_fin) {
        this.date_fin = date_fin;
    }

    public Boolean getBesoin_aide() {
        return besoin_aide;
    }

    public void setBesoin_aide(Boolean besoin_aide) {
        this.besoin_aide = besoin_aide;
    }
}
