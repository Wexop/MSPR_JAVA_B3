package fr.mspr_java_b3.controllers.mapper;

import fr.mspr_java_b3.dto.PlanteDTO;
import fr.mspr_java_b3.dto.PlanteGetDTO;
import fr.mspr_java_b3.entities.Plante;
import org.springframework.stereotype.Component;

@Component
public class PlanteMapper {

    public PlanteDTO toDTO(Plante plante) {
        if (plante == null) {
            return null;
        }
        PlanteDTO dto = new PlanteDTO();
        dto.setEspece(plante.getEspece());
        dto.setImage_url(plante.getImage_url());
        return dto;
    }

    public Plante toPlante(PlanteDTO planteDTO) {
        if (planteDTO == null) {
            return null;
        }
        Plante plante = new Plante();
        plante.setEspece(planteDTO.getEspece());
        plante.setImage_url(planteDTO.getImage_url());
        return plante;
    }

    public PlanteGetDTO toGetDTO(Plante plante) {
        if (plante == null) {
            return null;
        }
        PlanteGetDTO dto = new PlanteGetDTO();
        dto.setId(plante.getId());
        dto.setEspece(plante.getEspece());
        dto.setImage_url(plante.getImage_url());
        return dto;
    }

    public Plante toGetPlante(PlanteGetDTO planteGetDTO) {
        if (planteGetDTO == null) {
            return null;
        }
        Plante plante = new Plante();
        plante.setId(planteGetDTO.getId());
        plante.setEspece(planteGetDTO.getEspece());
        plante.setImage_url(planteGetDTO.getImage_url());
        return plante;
    }
}
