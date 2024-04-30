package fr.mspr_java_b3.dto;

import fr.mspr_java_b3.entities.AnnonceEnum;
import lombok.*;

@Getter
@Setter
@ToString
public class AnnonceDTO {
    private AnnonceEnum etat;
    private String description;
    private Boolean besoin_aide;
}
