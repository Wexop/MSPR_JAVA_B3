package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Garde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer annonce_id;

    private Integer proprio_id;

    private GardeEnum status;

    private LocalDateTime date_fin;

    private LocalDateTime date_debut;

    @ManyToOne
    @JoinColumn(name = "gardien_id")
    Utilisateur gardien;

    public Garde(int id, Utilisateur gardien, Integer annonce_id, Integer proprio_id, GardeEnum status, LocalDateTime date_fin, LocalDateTime date_debut) {
        this.id = id;
        this.gardien = gardien;
        this.annonce_id = annonce_id;
        this.proprio_id = proprio_id;
        this.status = status;
        this.date_fin = date_fin;
        this.date_debut = date_debut;
    }

    public Garde(Utilisateur gardien, Integer annonce_id, Integer proprio_id, GardeEnum status, LocalDateTime date_fin, LocalDateTime date_debut) {
        this.gardien = gardien;
        this.annonce_id = annonce_id;
        this.proprio_id = proprio_id;
        this.status = status;
        this.date_fin = date_fin;
        this.date_debut = date_debut;
    }

    public Garde() {
    }

    public Integer getAnnonce_id() {
        return annonce_id;
    }

    public void setAnnonce_id(Integer annonce_id) {
        this.annonce_id = annonce_id;
    }

    public Integer getProprio_id() {
        return proprio_id;
    }

    public void setProprio_id(Integer proprio_id) {
        this.proprio_id = proprio_id;
    }

    public GardeEnum getStatus() {
        return status;
    }

    public void setStatus(GardeEnum status) {
        this.status = status;
    }

    public LocalDateTime getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDateTime date_fin) {
        this.date_fin = date_fin;
    }

    public LocalDateTime getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDateTime date_debut) {
        this.date_debut = date_debut;
    }
}
