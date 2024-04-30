package fr.mspr_java_b3.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AnnonceListeDTO {
    private List<AnnonceGetDTO> annonceList;
}
