package fr.mspr_java_b3.controllers.mapper;

import fr.mspr_java_b3.dto.AnnonceMessageGetDTO;
import fr.mspr_java_b3.dto.AnnonceMessagePostDTO;
import fr.mspr_java_b3.entities.AnnonceMessage;
import org.springframework.stereotype.Component;

@Component
public class AnnonceMessageMapper {
    public AnnonceMessagePostDTO toAnnonceMessagePostDTO(AnnonceMessage annonceMessage) {
        if (annonceMessage == null) {
            return null;
        }
        AnnonceMessagePostDTO dto = new AnnonceMessagePostDTO();
        dto.setMessage(annonceMessage.getMessage());
        dto.setImage_url(annonceMessage.getImage_url());
        return dto;
    }

    public AnnonceMessage toAnnonceMessagePost(AnnonceMessagePostDTO dto) {
        if (dto == null) {
            return null;
        }
        AnnonceMessage annonceMessage = new AnnonceMessage();
        annonceMessage.setMessage(dto.getMessage());
        annonceMessage.setImage_url(dto.getImage_url());
        return annonceMessage;
    }

    public AnnonceMessageGetDTO toAnnonceMessageGetDTO(AnnonceMessage annonceMessage) {
        if (annonceMessage == null) {
            return null;
        }
        AnnonceMessageGetDTO dto = new AnnonceMessageGetDTO();
        dto.setId(annonceMessage.getId());
        dto.setImage_url(annonceMessage.getImage_url());
        dto.setMessage(annonceMessage.getMessage());
        dto.setDate(annonceMessage.getDate());
        dto.setUtilisateur(new UtilisateurMapper().toUtilisateurGetDTO(annonceMessage.getUtilisateur()));
        return dto;
    }

    public AnnonceMessage toAnnonceMessageGet(AnnonceMessageGetDTO dto) {
        if (dto == null) {
            return null;
        }

        AnnonceMessage annonceMessage = new AnnonceMessage();
        annonceMessage.setId(dto.getId());
        annonceMessage.setImage_url(dto.getImage_url());
        annonceMessage.setMessage(dto.getMessage());
        annonceMessage.setDate(dto.getDate());
        return annonceMessage;
    }
}
