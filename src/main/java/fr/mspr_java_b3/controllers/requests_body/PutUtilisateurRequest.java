package fr.mspr_java_b3.controllers.requests_body;

public class PutUtilisateurRequest {

    public String nom;

    public String image_url;

    public Boolean botaniste;

    public PutUtilisateurRequest() {
    }

    public PutUtilisateurRequest(String nom, String image_url, Boolean botaniste) {
        this.nom = nom;
        this.image_url = image_url;
        this.botaniste = botaniste;
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
