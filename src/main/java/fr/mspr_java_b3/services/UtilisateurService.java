package fr.mspr_java_b3.services;

import fr.mspr_java_b3.entities.Utilisateur;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UtilisateurService {

    public boolean checkMdp(Utilisateur user, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, user.getMdp());
    }

    public Utilisateur hashMdp(Utilisateur user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setMdp(passwordEncoder.encode(user.getMdp()));
        return user;
    }
}
