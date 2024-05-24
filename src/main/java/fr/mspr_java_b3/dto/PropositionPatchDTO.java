package fr.mspr_java_b3.dto;

import fr.mspr_java_b3.entities.PropositionEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PropositionPatchDTO {
    private String message;
    private PropositionEnum etat;
}
