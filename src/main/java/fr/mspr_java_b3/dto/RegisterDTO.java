package fr.mspr_java_b3.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class RegisterDTO {
    private String mail;
    private String nom;
    private String mdp;
}
