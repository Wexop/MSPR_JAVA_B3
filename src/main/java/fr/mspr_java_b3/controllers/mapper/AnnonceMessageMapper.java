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
        dto.setMessage(annonceMessage.getMessage());
        dto.setImage_url(annonceMessage.getImage_url());
        dto.setMessage_id(annonceMessage.getId());
        dto.setUsername(annonceMessage.getUtilisateur().getNom());
        dto.setUtilisateur(annonceMessage.getUtilisateur());
        dto.setDate(annonceMessage.getDate());
        return dto;
    }

    public AnnonceMessage toAnnonceMessageGet(AnnonceMessageGetDTO dto) {
        if (dto == null) {
            return null;
        }

        AnnonceMessage annonceMessage = new AnnonceMessage();
        annonceMessage.setMessage(dto.getMessage());
        annonceMessage.setImage_url(dto.getImage_url());
        annonceMessage.setId(dto.getMessage_id());
        annonceMessage.setUtilisateur(dto.getUtilisateur());
        annonceMessage.setDate(dto.getDate());
        return annonceMessage;
    }
}
