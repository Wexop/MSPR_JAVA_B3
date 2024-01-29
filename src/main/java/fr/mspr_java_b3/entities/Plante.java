package fr.mspr_java_b3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Plante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String espece;
    private String image_url;

    public Plante() {
    }

    public Plante(String espece, String image_url) {
        this.espece = espece;
        this.image_url = image_url;
    }

    public Plante(int id, String espece, String image_url) {
        this.id = id;
        this.espece = espece;
        this.image_url = image_url;
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
