package fr.mspr_java_b3.dto;

import fr.mspr_java_b3.entities.Adresse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UtilisateurGetDTO {
    private int id;
    private String nom;
    private String image_url;
    private Boolean botaniste;
    private String email;
    private Adresse adresse;
}
