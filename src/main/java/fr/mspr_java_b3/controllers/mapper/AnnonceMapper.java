package fr.mspr_java_b3.controllers.mapper;

import fr.mspr_java_b3.dto.AnnonceDTO;
import fr.mspr_java_b3.dto.AnnonceGetDTO;
import fr.mspr_java_b3.entities.Annonce;
import org.springframework.stereotype.Component;

@Component
public class AnnonceMapper {

    public AnnonceDTO toAnnonceDTO(Annonce annonce) {
        if (annonce == null) {
            return null;
        }
        AnnonceDTO dto = new AnnonceDTO();
        dto.setEtat(annonce.getEtat());
        dto.setDescription(annonce.getDescription());
        dto.setBesoin_aide(annonce.getBesoin_aide());
        dto.setTitre(annonce.getTitre());
        return dto;
    }

    public Annonce toAnnonce (AnnonceDTO annonceDTO) {
        if (annonceDTO == null) {
            return null;
        }
        Annonce annonce = new Annonce();
        annonce.setEtat(annonceDTO.getEtat());
        annonce.setDescription(annonceDTO.getDescription());
        annonce.setBesoin_aide(annonceDTO.getBesoin_aide());
        annonce.setTitre(annonceDTO.getTitre());
        return annonce;
    }

    public AnnonceGetDTO toAnnonceGetDTO(Annonce annonce) {
        if (annonce == null) {
            return null;
        }
        AnnonceGetDTO getDTO = new AnnonceGetDTO();
        getDTO.setId(annonce.getId());
        getDTO.setEtat(annonce.getEtat());
        getDTO.setDescription(annonce.getDescription());
        getDTO.setBesoin_aide(annonce.getBesoin_aide());
        getDTO.setTitre(annonce.getTitre());
        return getDTO;
    }

    public Annonce toGetAnnonce(AnnonceGetDTO annonceGetDTO) {
        if (annonceGetDTO == null) {
            return null;
        }
        Annonce annonce = new Annonce();
        annonce.setId(annonceGetDTO.getId());
        annonce.setEtat(annonceGetDTO.getEtat());
        annonce.setDescription(annonceGetDTO.getDescription());
        annonce.setBesoin_aide(annonceGetDTO.getBesoin_aide());
        annonce.setTitre(annonceGetDTO.getTitre());
        return annonce;
    }
}
