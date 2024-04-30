package fr.mspr_java_b3.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class LoginDTO {
    private String mail;
    private String mdp;
}
