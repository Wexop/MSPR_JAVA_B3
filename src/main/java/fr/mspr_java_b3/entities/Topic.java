package fr.mspr_java_b3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer utilisateur_id;

    private String titre;

    public Topic() {
    }

    public Topic(Integer user_id, String titre) {
        this.utilisateur_id = user_id;
        this.titre = titre;
    }

    public Topic(int id, Integer user_id, String titre) {
        this.id = id;
        this.utilisateur_id = user_id;
        this.titre = titre;
    }

    public Integer getUser_id() {
        return utilisateur_id;
    }

    public void setUser_id(Integer user_id) {
        this.utilisateur_id = user_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
