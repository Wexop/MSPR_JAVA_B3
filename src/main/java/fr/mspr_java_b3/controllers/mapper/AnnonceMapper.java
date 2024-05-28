package fr.mspr_java_b3.controllers.mapper;

import fr.mspr_java_b3.dto.AnnonceGetDTO;
import fr.mspr_java_b3.dto.AnnoncePostDTO;
import fr.mspr_java_b3.entities.Annonce;
import org.springframework.stereotype.Component;

@Component
public class AnnonceMapper {

    public AnnoncePostDTO toAnnoncePostDTO(Annonce annonce) {
        if (annonce == null) {
            return null;
        }
        AnnoncePostDTO dto = new AnnoncePostDTO();
        dto.setTitre(annonce.getTitre());
        dto.setDescription(annonce.getDescription());
        dto.setPlante(annonce.getPlante());
        return dto;
    }

    public Annonce toPostAnnonce (AnnoncePostDTO annonceDTO) {
        if (annonceDTO == null) {
            return null;
        }
        Annonce annonce = new Annonce();
        annonce.setTitre(annonceDTO.getTitre());
        annonce.setDescription(annonceDTO.getDescription());
        annonce.setPlante(annonceDTO.getPlante());
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
        getDTO.setAdresse(annonce.getUtilisateur().getAdresse());
        getDTO.setDate_debut(annonce.getDate_debut());
        getDTO.setDate_creation(annonce.getDate_creation());
        getDTO.setDate_fin(annonce.getDate_fin());
        getDTO.setUtilisateur_nom(annonce.getUtilisateur().getNom());
        getDTO.setPlante(new PlanteMapper().toGetPlanteDTO(annonce.getPlante()));
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
