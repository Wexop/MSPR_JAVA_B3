package fr.mspr_java_b3.entities;

import jakarta.persistence.*;

@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String adresse;
    private String latitude;
    private String longitude;

    @OneToOne(mappedBy = "adresse")
    Utilisateur utilisateur;

    public Adresse() {
    }

    public Adresse(String adresse, String latitude, String longitude) {
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Adresse(int id, String adresse, String latitude, String longitude) {
        this.id = id;
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

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


}
