package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.security.JwtUtil;

public class JwtUtilTest {
    public String getFakeToken() {
        JwtUtil jwtUtil = new JwtUtil();
        Utilisateur fakeUser = new Utilisateur();

        return jwtUtil.createToken(fakeUser);
    }
}
