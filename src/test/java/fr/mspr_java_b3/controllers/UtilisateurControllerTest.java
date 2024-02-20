package fr.mspr_java_b3.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mspr_java_b3.controllers.requests_body.LoginUserRequest;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.UtilisateurRepository;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UtilisateurController.class)
class UtilisateurControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UtilisateurRepository repository;

    @Test
    void postLogin() throws Exception {
        String email = "test@test.com";
        String password = "password";
        LoginUserRequest request = new LoginUserRequest(email, password);
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMdp(password);

        Mockito.when(repository.getUtilisateurByMail(email)).thenReturn(Optional.of(utilisateur));

        this.mvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mail", is(utilisateur.getMail())));

        verify(repository).getUtilisateurByMail(email);
    }

    @Test
    void postRegister() throws Exception {
        String email = "test15@test.com";
        String mdp = "mdp22";
        Utilisateur utilisateur = new Utilisateur(email, mdp, "test", "test", false);

        Mockito.when(repository.getUtilisateurByMail(email)).thenReturn(Optional.empty());

        Mockito.when(repository.save(utilisateur)).thenReturn(utilisateur);

        this.mvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(utilisateur)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mail", is(utilisateur.getMail())));

    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
