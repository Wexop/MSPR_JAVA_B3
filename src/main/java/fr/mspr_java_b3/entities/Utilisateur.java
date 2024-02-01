package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mail;

    private String mdp;

    private String nom;

    private String image_url;

    private Boolean botaniste;


    @OneToMany(mappedBy = "utilisateur")
    Set<Plante> plantes;

    @OneToMany(mappedBy = "utilisateur")
    Set<Annonce> annonces;

    @OneToOne(mappedBy = "utilisateur")
    Bibliotheque bibliotheque;

    @OneToMany(mappedBy = "utilisateur")
    Set<AnnonceMessage> annonceMessages;

    @OneToMany(mappedBy = "utilisateur")
    Set<Proposition> propositions;

    @OneToMany(mappedBy = "utilisateur")
    Set<Article> articles;

    @OneToMany(mappedBy = "utilisateur")
    Set<Commentaire> commentaires;

    @OneToOne(mappedBy = "utilisateur")
    Adresse adresse;

    public Utilisateur() {
    }

    public Utilisateur(String mail, String mdp, String nom, String image_url, Boolean botaniste) {
        this.mail = mail;
        this.mdp = mdp;
        this.nom = nom;
        this.image_url = image_url;
        this.botaniste = botaniste;
    }

    public Utilisateur(int id, String mail, String mdp, String nom, String image_url, Boolean botaniste) {
        this.id = id;
        this.mail = mail;
        this.mdp = mdp;
        this.nom = nom;
        this.image_url = image_url;
        this.botaniste = botaniste;
    }

    public int getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Boolean getBotaniste() {
        return botaniste;
    }

    public void setBotaniste(Boolean botaniste) {
        this.botaniste = botaniste;
    }
}
