package fr.mspr_java_b3.controllers.mapper;

import fr.mspr_java_b3.dto.LoginDTO;
import fr.mspr_java_b3.dto.RegisterDTO;
import fr.mspr_java_b3.dto.UtilisateurGetDTO;
import fr.mspr_java_b3.entities.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {
    public UtilisateurGetDTO toUtilisateurGetDTO(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }
        UtilisateurGetDTO dto = new UtilisateurGetDTO();
        dto.setNom(utilisateur.getNom());
        dto.setImage_url(utilisateur.getImage_url());
        dto.setBotaniste(utilisateur.getBotaniste());
        dto.setAdresse(utilisateur.getAdresse());
        dto.setEmail(utilisateur.getMail());
        return dto;
    }

    public Utilisateur toUtilisateur(UtilisateurGetDTO dto) {
        if (dto == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(dto.getNom());
        utilisateur.setImage_url(dto.getImage_url());
        utilisateur.setBotaniste(dto.getBotaniste());
        return utilisateur;
    }


    public LoginDTO toLoginDTO(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }
        LoginDTO dto = new LoginDTO();
        dto.setMail(utilisateur.getMail());
        dto.setMdp(utilisateur.getMdp());
        return dto;
    }

    public Utilisateur toLoginUtilisateur(LoginDTO dto) {
        if (dto == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMail(dto.getMail());
        utilisateur.setMdp(dto.getMdp());
        return utilisateur;
    }

    public RegisterDTO toRegisterDTO(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        RegisterDTO dto = new RegisterDTO();
        dto.setMail(utilisateur.getMail());
        dto.setMdp(utilisateur.getMdp());
        dto.setNom(utilisateur.getNom());
        return dto;
    }

    public Utilisateur toRegisterUtilisateur(RegisterDTO dto) {
        if (dto == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMail(dto.getMail());
        utilisateur.setNom(dto.getNom());
        utilisateur.setMdp(dto.getMdp());
        return utilisateur;
    }
}
