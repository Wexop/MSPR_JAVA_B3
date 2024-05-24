package fr.mspr_java_b3.controllers.mapper;

import fr.mspr_java_b3.dto.AdresseDTO;
import fr.mspr_java_b3.entities.Adresse;
import org.springframework.stereotype.Component;

@Component
public class AdresseMapper {

    public AdresseDTO toAdresseDTO(Adresse adresse) {
        if (adresse == null) {
            return null;
        }
        AdresseDTO dto = new AdresseDTO();
        dto.setLatitude(adresse.getLatitude());
        dto.setLongitude(adresse.getLongitude());
        dto.setAdresse(adresse.getAdresse());
        return dto;
    }

    public Adresse toAdresse(AdresseDTO adresseDTO) {
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
