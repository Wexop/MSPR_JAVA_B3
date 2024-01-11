package fr.mspr_java_b3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String adresse;
    private String latitude;
    private String longitude;
    private Integer utilisateur_id;

    public Adresse() {
    }

    public Adresse(String adresse, String latitude, String longitude, Integer utilisateur_id) {
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
        this.utilisateur_id = utilisateur_id;
    }

    public Adresse(int id, String adresse, String latitude, String longitude, Integer utilisateur_id) {
        this.id = id;
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
        this.utilisateur_id = utilisateur_id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(Integer utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }
}
