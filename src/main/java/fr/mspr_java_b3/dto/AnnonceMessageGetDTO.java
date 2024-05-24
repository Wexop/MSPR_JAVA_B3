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
public class AnnonceMessageGetDTO {
    private String message;
    private String image_url;
    private int message_id;
    private String username;
    private Utilisateur utilisateur;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime date;
}
