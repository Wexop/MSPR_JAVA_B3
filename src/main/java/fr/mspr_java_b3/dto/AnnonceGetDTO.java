package fr.mspr_java_b3.dto;

import fr.mspr_java_b3.entities.Adresse;
import fr.mspr_java_b3.entities.AnnonceEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AnnonceGetDTO {
    private int id;
    private AnnonceEnum etat;
    private String description;
    private Boolean besoin_aide;
    private String titre;
    private Adresse adresse;
    private LocalDateTime date_creation;
    private LocalDateTime date_debut;
    private LocalDateTime date_fin;
    private String utilisateur_nom;
    private PlanteGetDTO plante;
}
