package fr.mspr_java_b3.controllers.requests_body;

import fr.mspr_java_b3.entities.Adresse;

public class PutUtilisateurRequest {

    public String nom;

    public String image_url;

    public Boolean botaniste;

    public Adresse adresse;

    public PutUtilisateurRequest(String nom, String image_url, Boolean botaniste, Adresse adresse) {
        this.nom = nom;
        this.image_url = image_url;
        this.botaniste = botaniste;
        this.adresse = adresse;
    }

    public PutUtilisateurRequest() {
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
