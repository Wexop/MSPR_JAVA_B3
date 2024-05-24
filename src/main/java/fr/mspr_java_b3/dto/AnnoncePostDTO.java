package fr.mspr_java_b3.dto;

import fr.mspr_java_b3.entities.AnnonceEnum;
import lombok.*;

@Getter
@Setter
@ToString
public class AnnoncePostDTO {
    private AnnonceEnum etat;
    private String description;
    private Boolean besoin_aide;
    private String titre;
}
