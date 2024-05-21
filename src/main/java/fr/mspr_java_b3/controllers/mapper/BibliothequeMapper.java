package fr.mspr_java_b3.controllers.mapper;

import fr.mspr_java_b3.dto.BibliothequeDTO;
import fr.mspr_java_b3.dto.BibliothequeGetDTO;
import fr.mspr_java_b3.entities.Bibliotheque;

public class BibliothequeMapper {

    public BibliothequeDTO toBibliothequeDTO(Bibliotheque bibliotheque) {
        if (bibliotheque == null) {
            return null;
        }
        BibliothequeDTO dto = new BibliothequeDTO();
        dto.setImage_url(bibliotheque.getImage_url());
        dto.setTitre(bibliotheque.getTitre());
        return dto;
    }

    public Bibliotheque toBibliotheque(BibliothequeDTO dto) {
        if (dto == null) {
            return null;
        }
        Bibliotheque bibliotheque = new Bibliotheque();
        bibliotheque.setImage_url(dto.getImage_url());
        bibliotheque.setTitre(dto.getTitre());
        return bibliotheque;
    }

    public BibliothequeGetDTO toBibliothequeGetDTO(Bibliotheque bibliotheque) {
        if (bibliotheque == null) {
            return null;
        }
        BibliothequeGetDTO dto = new BibliothequeGetDTO();
        dto.setId(bibliotheque.getId());
        dto.setImage_url(bibliotheque.getImage_url());
        dto.setTitre(bibliotheque.getTitre());
        return dto;
    }

    public Bibliotheque toGetBibliotheque(BibliothequeGetDTO dto) {
        if (dto == null) {
            return null;
        }
        Bibliotheque bibliotheque = new Bibliotheque();
        bibliotheque.setId(dto.getId());
        bibliotheque.setImage_url(dto.getImage_url());
        bibliotheque.setTitre(dto.getTitre());
        return bibliotheque;
    }
}
