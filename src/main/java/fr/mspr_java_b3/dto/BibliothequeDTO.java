package fr.mspr_java_b3.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class BibliothequeDTO {
    private String image_url;
    private String titre;
}
