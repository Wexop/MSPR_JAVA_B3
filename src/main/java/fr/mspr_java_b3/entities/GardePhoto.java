package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

@Entity
public class GardePhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer garde_id;

    private String image_url;

    public GardePhoto(int id, Integer garde_id, String image_url) {
        this.id = id;
        this.garde_id = garde_id;
        this.image_url = image_url;
    }

    public GardePhoto(Integer garde_id, String image_url) {
        this.garde_id = garde_id;
        this.image_url = image_url;
    }

    public GardePhoto() {
    }

    public Integer getGarde_id() {
        return garde_id;
    }

    public void setGarde_id(Integer garde_id) {
        this.garde_id = garde_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
