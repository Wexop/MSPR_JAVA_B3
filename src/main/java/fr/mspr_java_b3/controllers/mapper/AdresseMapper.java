package fr.mspr_java_b3.controllers.mapper;

import fr.mspr_java_b3.dto.AdressePostDTO;
import fr.mspr_java_b3.entities.Adresse;
import org.springframework.stereotype.Component;

@Component
public class AdresseMapper {

    public AdressePostDTO toAdresseDTO(Adresse adresse) {
        if (adresse == null) {
            return null;
        }
        AdressePostDTO dto = new AdressePostDTO();
        dto.setLatitude(adresse.getLatitude());
        dto.setLongitude(adresse.getLongitude());
        dto.setAdresse(adresse.getAdresse());
        return dto;
    }

    public Adresse toAdresse(AdressePostDTO adresseDTO) {
        if (adresseDTO == null) {
            return null;
        }
        Adresse adresse = new Adresse();
        adresse.setLatitude(adresseDTO.getLatitude());
        adresse.setLongitude(adresseDTO.getLongitude());
        adresse.setAdresse(adresseDTO.getAdresse());
        return adresse;
    }
}
