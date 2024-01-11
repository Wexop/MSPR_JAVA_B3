package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Garde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer gardien_id;

    private Integer annonce_id;

    private Integer proprio_id;

    private GardeEnum status;

    private LocalDateTime date_fin;

    private LocalDateTime date_debut;

    public Garde(int id, Integer gardien_id, Integer annonce_id, Integer proprio_id, GardeEnum status, LocalDateTime date_fin, LocalDateTime date_debut) {
        this.id = id;
        this.gardien_id = gardien_id;
        this.annonce_id = annonce_id;
        this.proprio_id = proprio_id;
        this.status = status;
        this.date_fin = date_fin;
        this.date_debut = date_debut;
    }

    public Garde(Integer gardien_id, Integer annonce_id, Integer proprio_id, GardeEnum status, LocalDateTime date_fin, LocalDateTime date_debut) {
        this.gardien_id = gardien_id;
        this.annonce_id = annonce_id;
        this.proprio_id = proprio_id;
        this.status = status;
        this.date_fin = date_fin;
        this.date_debut = date_debut;
    }

    public Garde() {
    }

    public Integer getGardien_id() {
        return gardien_id;
    }

    public void setGardien_id(Integer gardien_id) {
        this.gardien_id = gardien_id;
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
