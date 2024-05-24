package fr.mspr_java_b3.controllers.mapper;

import fr.mspr_java_b3.dto.AuthDTO;
import fr.mspr_java_b3.dto.LoginDTO;
import fr.mspr_java_b3.dto.RegisterDTO;
import fr.mspr_java_b3.entities.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {
    public LoginDTO toLoginDTO(Utilisateur utilisateur) {
        if (utilisateur != null) {
            return null;
        }
        LoginDTO dto = new LoginDTO();
        dto.setMail(utilisateur.getMail());
        dto.setMdp(utilisateur.getMdp());
        return dto;
    }

    public Utilisateur toLoginUtilisateur(LoginDTO dto) {
        if (dto != null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMail(dto.getMail());
        utilisateur.setMdp(dto.getMdp());
        return utilisateur;
    }

    public RegisterDTO toRegisterDTO(Utilisateur utilisateur) {
        if (utilisateur != null) {
            return null;
        }

        RegisterDTO dto = new RegisterDTO();
        dto.setMail(utilisateur.getMail());
        dto.setMdp(utilisateur.getMdp());
        dto.setNom(utilisateur.getNom());
        return dto;
    }

    public Utilisateur toRegisterUtilisateur(RegisterDTO dto) {
        if (dto != null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMail(dto.getMail());
        utilisateur.setNom(dto.getNom());
        utilisateur.setMdp(dto.getMdp());
        return utilisateur;
    }
}
