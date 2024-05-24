package fr.mspr_java_b3.dto;

import fr.mspr_java_b3.entities.Plante;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnnoncePostDTO {
    private String titre;
    private String description;
    private Plante plante;
}
