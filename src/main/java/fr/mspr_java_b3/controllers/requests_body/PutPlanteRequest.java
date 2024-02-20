package fr.mspr_java_b3.controllers.requests_body;

public class PutPlanteRequest {

    public String espece;
    public String image_url;

    public PutPlanteRequest() {
    }

    public PutPlanteRequest(String image_url, String espece) {
        this.image_url = image_url;
        this.espece = espece;
    }
}
