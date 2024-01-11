package fr.mspr_java_b3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PlanteUtilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer plante_id;
    private Integer utilisateur_id;

    public PlanteUtilisateur() {
    }

    public PlanteUtilisateur(Integer plante_id, Integer utilisateur_id) {
        this.plante_id = plante_id;
        this.utilisateur_id = utilisateur_id;
    }

    public PlanteUtilisateur(int id, Integer plante_id, Integer utilisateur_id) {
        this.id = id;
        this.plante_id = plante_id;
        this.utilisateur_id = utilisateur_id;
    }

    public Integer getPlante_id() {
        return plante_id;
    }

    public void setPlante_id(Integer plante_id) {
        this.plante_id = plante_id;
    }

    public Integer getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(Integer utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }
}
