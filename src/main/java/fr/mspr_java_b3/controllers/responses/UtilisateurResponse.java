package fr.mspr_java_b3.controllers.responses;

import fr.mspr_java_b3.entities.Adresse;
import fr.mspr_java_b3.entities.Utilisateur;

public class UtilisateurResponse {

    public String mail;
    public String nom;
    public String image_url;
    public boolean botaniste;
    public Adresse adresse;

    public UtilisateurResponse() {
    }

    public UtilisateurResponse(String mail, String nom, String image_url, boolean botaniste, Adresse adresse) {
        this.mail = mail;
        this.nom = nom;
        this.image_url = image_url;
        this.botaniste = botaniste;
        this.adresse = adresse;
    }

    public void fromUser(Utilisateur user) {
        this.botaniste = user.getBotaniste();
        this.adresse = user.getAdresse();
        this.nom = user.getNom();
        this.mail = user.getMail();
        this.image_url = user.getImage_url();
    }
}
