package fr.mspr_java_b3.entities;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @OneToMany(mappedBy = "utilisateur")
    Set<Bibliotheque> bibliotheques;

    @OneToMany(mappedBy = "utilisateur")
    Set<AnnonceMessage> annonceMessages;

    @OneToMany(mappedBy = "utilisateur")
    Set<Proposition> propositions;

    @OneToMany(mappedBy = "utilisateur")
    Set<Article> articles;

    @OneToMany(mappedBy = "utilisateur")
    Set<Commentaire> commentaires;

    @OneToOne
    @JoinColumn(name = "adresse_id")
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
        this.mdp = hashMdp(mdp);
        this.nom = nom;
        this.image_url = image_url;
        this.botaniste = botaniste;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setId(int id) {
        this.id = id;
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


    public void setMdp(String mdp) {
        this.mdp = hashMdp(mdp);
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

    public boolean checkMdp(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, this.mdp);
    }

    public String hashMdp(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.mdp = passwordEncoder.encode(password);
        return this.mdp;
    }
}
