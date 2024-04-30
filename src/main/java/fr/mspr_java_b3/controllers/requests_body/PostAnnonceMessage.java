package fr.mspr_java_b3.controllers.requests_body;

import java.time.LocalDateTime;

public class PostAnnonceMessage {

    private String image_url;
    private String message;

    public PostAnnonceMessage() {
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
