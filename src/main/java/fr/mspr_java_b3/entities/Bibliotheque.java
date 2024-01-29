package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

@Entity
public class Bibliotheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String image_url;

    private Integer utilisateur_id;

    private String titre;

    public Bibliotheque(int id, String image_url, Integer utilisateur_id, String titre) {
        this.id = id;
        this.image_url = image_url;
        this.utilisateur_id = utilisateur_id;
        this.titre = titre;
    }

    public Bibliotheque(String image_url, Integer utilisateur_id, String titre) {
        this.image_url = image_url;
        this.utilisateur_id = utilisateur_id;
        this.titre = titre;
    }

    public Bibliotheque() {
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Integer getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(Integer utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
