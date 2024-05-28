package fr.mspr_java_b3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PutPasswordUserDTO {

    String newMdp;
    String mdp;
}
