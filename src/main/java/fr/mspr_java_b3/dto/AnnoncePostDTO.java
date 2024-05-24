package fr.mspr_java_b3.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.mspr_java_b3.entities.AnnonceEnum;
import fr.mspr_java_b3.entities.Plante;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AnnoncePostDTO {
    private String titre;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime date_debut;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime date_fin;
    private Plante plante;
}
