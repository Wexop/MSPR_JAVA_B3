package fr.mspr_java_b3.controllers.mapper;

import fr.mspr_java_b3.controllers.responses.AuthResponse;
import fr.mspr_java_b3.dto.AuthDTO;
import fr.mspr_java_b3.entities.Utilisateur;

public class AuthMapper {

    public AuthDTO toAuthDTO(AuthResponse authResponse) {
        if (authResponse != null) {
            return null;
        }
        AuthDTO dto = new AuthDTO();
        dto.setToken(authResponse.getToken());
        return dto;
    }
    public AuthResponse toAuthUtilisateur(AuthDTO dto) {
        if (dto != null) {
            return null;
        }
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(dto.getToken());
        return authResponse;
    }
}
