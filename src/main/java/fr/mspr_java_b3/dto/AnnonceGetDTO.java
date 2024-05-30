package fr.mspr_java_b3.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime date_creation;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime date_debut;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime date_fin;
    private String utilisateur_nom;
    private PlanteGetDTO plante;
    private UtilisateurGetDTO utilisateurGarde;
}
