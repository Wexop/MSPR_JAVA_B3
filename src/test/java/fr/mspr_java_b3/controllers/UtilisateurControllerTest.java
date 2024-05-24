package fr.mspr_java_b3.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mspr_java_b3.controllers.requests_body.LoginUserRequest;
import fr.mspr_java_b3.entities.Adresse;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.AdresseRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import fr.mspr_java_b3.services.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UtilisateurControllerAChanger.class)
class UtilisateurControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UtilisateurRepository repository;

    @MockBean
    private AdresseRepository adresseRepository;

    String token = new JwtUtilTest().getFakeToken();

    @Test
    void postLogin() throws Exception {
        String email = "test@test.com";
        String password = "password";
        LoginUserRequest request = new LoginUserRequest(email, password);
        Utilisateur utilisateur = new Utilisateur();
        UtilisateurService utilisateurService = new UtilisateurService();
        utilisateur.setMail(email);
        utilisateur.setMdp(password);
        Utilisateur hashedUser = utilisateurService.hashMdp(utilisateur);


        Mockito.when(repository.getUtilisateurByMail(email)).thenReturn(Optional.of(hashedUser));

        this.mvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").exists());

        verify(repository).getUtilisateurByMail(email);
    }

    @Test
    void postRegister() throws Exception {
        Adresse adresse = new Adresse();
        String email = "test15@test.com";
        String mdp = "mdp22";
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMail(email);
        utilisateur.setMdp(mdp);
        utilisateur.setAdresse(adresse);

        Mockito.when(repository.getUtilisateurByMail(email)).thenReturn(Optional.empty());

        Mockito.when(repository.save(any(Utilisateur.class))).thenReturn(utilisateur);

        this.mvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(utilisateur)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(repository).getUtilisateurByMail(email);
        verify(repository).save(any(Utilisateur.class));

    }

    @Test
    void getUtilisateur() throws Exception {
        int utilisateurId = 1;
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurId);

        Mockito.when(repository.findById(utilisateurId)).thenReturn(Optional.of(utilisateur));

        this.mvc.perform(get("/utilisateur/me")
                .header("Utilisateur_id", String.valueOf(utilisateurId))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(utilisateurId));

        verify(repository).findById(utilisateurId);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
