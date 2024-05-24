package fr.mspr_java_b3.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.mspr_java_b3.entities.Utilisateur;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ArticleGetDTO {
    private int id;
    private String contenue;
    private String titre;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime date;
    private UtilisateurGetDTO utilisateur;
}
