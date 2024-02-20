package fr.mspr_java_b3.controllers.requests_body;

import fr.mspr_java_b3.entities.AnnonceEnum;

public class PutAnnonceRequest {

    public AnnonceEnum etat;

    public String description;
    public String titre;

    public boolean besoin_aide;

    public PutAnnonceRequest() {
    }

    public PutAnnonceRequest(AnnonceEnum etat, String description, String titre, boolean besoin_aide) {
        this.etat = etat;
        this.description = description;
        this.titre = titre;
        this.besoin_aide = besoin_aide;
    }
}
