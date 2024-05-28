package fr.mspr_java_b3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PutUtilisateurDTO {

    public String nom;

    public String image_url;

    public Boolean botaniste;

    public AdressePostDTO adresse;
}
