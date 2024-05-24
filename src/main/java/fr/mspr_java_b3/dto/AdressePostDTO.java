package fr.mspr_java_b3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdressePostDTO {
    private String latitude;
    private String longitude;
    private String adresse;
}
