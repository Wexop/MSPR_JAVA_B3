package fr.mspr_java_b3.controllers.mapper;

import fr.mspr_java_b3.dto.PropositionGetDTO;
import fr.mspr_java_b3.dto.PropositionPatchDTO;
import fr.mspr_java_b3.dto.PropositionPostDTO;
import fr.mspr_java_b3.entities.Proposition;
import org.springframework.stereotype.Component;

@Component
public class PropositionMapper {

    public PropositionPostDTO toPropositionPostDTO(Proposition proposition) {
        if (proposition == null) {
            return null;
        }

        PropositionPostDTO dto = new PropositionPostDTO();
        dto.setMessage(proposition.getMessage());
        return dto;
    }

    public Proposition toPropositionPost(PropositionPostDTO dto) {
        if (dto == null) {
            return null;
        }

        Proposition proposition = new Proposition();
        proposition.setMessage(dto.getMessage());
        return proposition;
    }

    public PropositionGetDTO toPropositionGetDTO(Proposition proposition) {
        if (proposition == null) {
            return null;
        }

        PropositionGetDTO dto = new PropositionGetDTO();
        dto.setId(proposition.getId());
        dto.setDate(proposition.getDate());
        dto.setMessage(proposition.getMessage());
        dto.setEtat(proposition.getEtat());
        dto.setUtilisateurProposition(new UtilisateurMapper().toUtilisateurGetDTO(proposition.getUtilisateur()));
        dto.setAnnonce(new AnnonceMapper().toAnnonceGetDTO(proposition.getAnnonce()));
        return dto;
    }

    public Proposition toPropositionGet(PropositionGetDTO dto) {
        if (dto == null) {
            return null;
        }
        Proposition proposition = new Proposition();
        proposition.setId(dto.getId());
        proposition.setDate(dto.getDate());
        proposition.setMessage(dto.getMessage());
        proposition.setEtat(dto.getEtat());
        return proposition;
    }

    public PropositionPatchDTO toPropositionPatchDTO(Proposition proposition) {
        if (proposition == null) {
            return null;
        }

        PropositionPatchDTO dto = new PropositionPatchDTO();
        dto.setMessage(proposition.getMessage());
        dto.setEtat(proposition.getEtat());
        return dto;
    }

    public Proposition toPropositionPatch(PropositionPatchDTO dto, Proposition proposition) {
        if (dto == null) {
            return null;
        }

        if (dto.getMessage() != null) proposition.setMessage(dto.getMessage());
        if (dto.getEtat() != null) proposition.setEtat(dto.getEtat());
        return proposition;
    }
}
