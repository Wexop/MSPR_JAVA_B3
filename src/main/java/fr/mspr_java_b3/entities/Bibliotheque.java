package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

@Entity
public class Bibliotheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String image_url;

    private String titre;

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    Utilisateur utilisateur;

    public Bibliotheque() {
    }

    public Bibliotheque(String image_url, String titre, Utilisateur utilisateur) {
        this.image_url = image_url;
        this.titre = titre;
        this.utilisateur = utilisateur;
    }

    public Bibliotheque(int id, String image_url, String titre, Utilisateur utilisateur) {
        this.id = id;
        this.image_url = image_url;
        this.titre = titre;
        this.utilisateur = utilisateur;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
