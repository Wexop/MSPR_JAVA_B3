package fr.mspr_java_b3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UtilisateurGetDTO {
    private String nom;
    private String image_url;
    private Boolean botaniste;
}
