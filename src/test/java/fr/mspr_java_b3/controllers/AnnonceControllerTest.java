package fr.mspr_java_b3.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mspr_java_b3.dto.AnnonceGetDTO;
import fr.mspr_java_b3.dto.AnnoncePostDTO;
import fr.mspr_java_b3.entities.AnnonceEnum;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import fr.mspr_java_b3.services.AnnonceService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AnnonceController.class)
public class AnnonceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AnnonceService annonceService;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    String token = new JwtUtilTest().getFakeToken();

    @Test
    void getAnnonceById() throws Exception {
        int annonceId = 1;
        AnnonceGetDTO annonceGetDTO = new AnnonceGetDTO();
        annonceGetDTO.setId(annonceId);

        Mockito.when(annonceService.getAnnonceById(annonceId)).thenReturn(annonceGetDTO);

        mvc.perform(get("/annonces/{id}", annonceId).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(annonceGetDTO.getId())));

        verify(annonceService).getAnnonceById(annonceId);
    }

    @Test
    void getAnnonce_attente() throws Exception {
        final AnnonceGetDTO annonce = new AnnonceGetDTO();
        annonce.setEtat(AnnonceEnum.en_attente);

        final List<AnnonceGetDTO> annonces = List.of(annonce);

        Mockito.when(annonceService.getAnnonceAttente()).thenReturn(annonces);

        mvc.perform(get("/annonces_attente").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].etat", is(annonce.getEtat().toString())));

        verify(annonceService).getAnnonceAttente();
    }

    @Test
    void getAnnonceAide() throws Exception {
        final AnnonceGetDTO annonce = new AnnonceGetDTO();
        annonce.setEtat(AnnonceEnum.en_cours);

        final List<AnnonceGetDTO> annonces = List.of(annonce);

        Mockito.when(annonceService.getAnnonceAide()).thenReturn(annonces);

        mvc.perform(get("/annonces_aide").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].etat", is(annonce.getEtat().toString())));

        verify(annonceService).getAnnonceAide();
    }

    @Test
    void getMesAnnonces() throws Exception {
        String utilisateurId = "1";
        List<AnnonceGetDTO> annonces = Arrays.asList(new AnnonceGetDTO(), new AnnonceGetDTO());

        Mockito.when(annonceService.getMesAnnonces(utilisateurId)).thenReturn(annonces);

        mvc.perform(get("/annonces/me").header("Authorization", "Bearer " + token)
                        .header("Utilisateur_id", String.valueOf(utilisateurId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(annonces.size())));

        verify(annonceService).getMesAnnonces(utilisateurId);
    }

    @Test
    void getMesGardes() throws Exception {
        String utilisateurGarde = "1";
        List<AnnonceGetDTO> annonces = Arrays.asList(new AnnonceGetDTO(), new AnnonceGetDTO());

        Mockito.when(annonceService.getMesGardes(utilisateurGarde)).thenReturn(annonces);

        mvc.perform(get("/annonces_garde/me").header("Authorization", "Bearer " + token)
                        .header("Authorization", String.valueOf(utilisateurGarde)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(annonces.size())));

        verify(annonceService).getMesGardes(utilisateurGarde);
    }

    @Test
    void postAnnonce() throws Exception {
        AnnoncePostDTO annoncePostDTO = new AnnoncePostDTO();
        AnnonceGetDTO annonceGetDTO = new AnnonceGetDTO();
        annonceGetDTO.setEtat(AnnonceEnum.en_attente);

        Mockito.when(annonceService.postAnnonce(any(AnnoncePostDTO.class), anyInt())).thenReturn(annonceGetDTO);

        Utilisateur user = new Utilisateur();
        user.setId(1);
        Mockito.when(utilisateurRepository.findById(anyInt())).thenReturn(Optional.of(user));

        mvc.perform(post("/annonces").header("Authorization", "Bearer " + token)
                        .content(asJsonString(annoncePostDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.etat", is(annonceGetDTO.getEtat().toString())));

        verify(annonceService).postAnnonce(any(AnnoncePostDTO.class), anyInt());
    }

    /*@Test
    void patchAnnonce() throws Exception {
        int annonceId = 1;
        Annonce annonce = new Annonce();
        annonce.setId(annonceId);
        annonce.setBesoin_aide(true);
        annonce.setEtat(termine);
        annonce.setTitre("Test");
        annonce.setDescription("test-description");

        Mockito.when(repository.getReferenceById(annonceId)).thenReturn(annonce);
        Mockito.when(repository.save(any(Annonce.class))).thenAnswer(e -> e.getArgument(0));

        this.mvc.perform(put("/annonce/{id}", annonceId).header("Authorization", "Bearer " + token)
                .content(asJsonString(annonce))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(annonceId));

        verify(repository).getReferenceById(annonceId);
        verify(repository).save(annonce);
    }*/

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
