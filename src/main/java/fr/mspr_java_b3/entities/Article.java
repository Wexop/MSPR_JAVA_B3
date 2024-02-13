package fr.mspr_java_b3.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;
    private String contenu;
    private String image_url;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    Utilisateur utilisateur;

    @OneToMany(mappedBy = "article")
    Set<Commentaire> commentaires;

    public Article() {
    }

    public Article(int id, String titre, String contenu, String image_url, LocalDateTime date) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.image_url = image_url;
        this.date = date;
    }

    public Article(String titre, String contenu, String image_url, LocalDateTime date) {
        this.titre = titre;
        this.contenu = contenu;
        this.image_url = image_url;
        this.date = date;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
