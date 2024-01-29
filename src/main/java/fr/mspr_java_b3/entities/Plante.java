package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Plante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String espece;
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    Utilisateur utilisateur;

    @OneToMany(mappedBy = "plante")
    Set<Annonce> annonces;

    public Plante() {
    }

    public Plante(String espece, String image_url, Utilisateur utilisateur) {
        this.espece = espece;
        this.image_url = image_url;
        this.utilisateur = utilisateur;
    }

    public Plante(int id, String espece, String image_url, Utilisateur utilisateur) {
        this.id = id;
        this.espece = espece;
        this.image_url = image_url;
        this.utilisateur = utilisateur;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

}
