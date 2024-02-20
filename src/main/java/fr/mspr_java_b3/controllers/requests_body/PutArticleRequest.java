package fr.mspr_java_b3.controllers.requests_body;

public class PutArticleRequest {

    public String titre;
    public String contenu;

    public PutArticleRequest() {
    }

    public PutArticleRequest(String titre, String contenu) {
        this.titre = titre;
        this.contenu = contenu;
    }
}
