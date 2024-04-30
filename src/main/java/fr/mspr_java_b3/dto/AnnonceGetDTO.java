package fr.mspr_java_b3.dto;

import fr.mspr_java_b3.entities.AnnonceEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AnnonceGetDTO {
    private int id;
    private AnnonceEnum etat;
    private String description;
    private Boolean besoin_aide;
}
