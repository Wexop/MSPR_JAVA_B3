package fr.mspr_java_b3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterDTO {
    private String mail;
    private String nom;
    private String mdp;
    private AdresseDTO adresse;
}
