package fr.mspr_java_b3.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mspr_java_b3.entities.Bibliotheque;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.BibliothequeRepository;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BibliothequeController.class)
class BibliothequeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BibliothequeRepository repository;

    @MockBean
    UtilisateurRepository utilisateurRepository;

    String token = new JwtUtilTest().getFakeToken();

    @Test
    void getBibliotheque() throws Exception {
        int utilisateurId = 1;
        Bibliotheque bibliotheque = new Bibliotheque();
        List<Bibliotheque> bibliotheques = Arrays.asList(bibliotheque);

        Mockito.when(repository.getBibliothequeByUtilisateur(utilisateurId)).thenReturn(bibliotheques);

        this.mvc.perform(get("/bibliotheque/me")
                .header("Utilisateur_id", String.valueOf(utilisateurId)).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists());

        verify(repository).getBibliothequeByUtilisateur(utilisateurId);
    }

    @Test
    void postBibliotheque() throws Exception {
        int utilisateur_id = 1;
        Bibliotheque bibliotheque = new Bibliotheque();
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateur_id);

        Mockito.when(utilisateurRepository.findById(utilisateur_id)).thenReturn(Optional.of(utilisateur));
        Mockito.when(repository.save(any(Bibliotheque.class))).thenReturn(bibliotheque);

        this.mvc.perform(post("/bibliotheque/one")
                .header("Utilisateur_id", String.valueOf(utilisateur_id))
                .header("Authorization", "Bearer " + token)
                .content(asJsonString(bibliotheque))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

        verify(utilisateurRepository).findById(utilisateur_id);
        verify(repository).save(any(Bibliotheque.class));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
