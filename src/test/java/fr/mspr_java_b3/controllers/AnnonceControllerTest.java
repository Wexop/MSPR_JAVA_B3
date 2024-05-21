package fr.mspr_java_b3.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mspr_java_b3.entities.Annonce;
import fr.mspr_java_b3.entities.AnnonceEnum;
import fr.mspr_java_b3.repository.AnnonceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static fr.mspr_java_b3.entities.AnnonceEnum.termine;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AnnonceControllerAChanger.class)
class AnnonceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AnnonceRepository repository;

    String token = new JwtUtilTest().getFakeToken();


    @Test
    void getAnnonce_attente() throws Exception {
        final Annonce annonce = new Annonce();
        annonce.setEtat(AnnonceEnum.en_attente);

        final List<Annonce> annonces = Arrays.asList(annonce);

        Mockito.when(this.repository.findByEtat(AnnonceEnum.en_attente)).thenReturn(annonces);

        this.mvc.perform(get("/annonce_attente").header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].etat", is(annonce.getEtat().toString())));

        verify(repository).findByEtat(AnnonceEnum.en_attente);
    }

    @Test
    void getAnnonce_aide() throws Exception {
        final Annonce annonce = new Annonce();
        annonce.setEtat(AnnonceEnum.en_cours);

        final List<Annonce> annonces = Arrays.asList(annonce);

        Mockito.when(this.repository.findNeedHelp(AnnonceEnum.en_cours)).thenReturn(annonces);

        this.mvc.perform(get("/annonce_aide").header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].etat", is(annonce.getEtat().toString())));

        verify(repository).findNeedHelp(AnnonceEnum.en_cours);
    }

    @Test
    void getAnnonce_by_id() throws Exception {
        int annonceId = 1;
        final Annonce annonce = new Annonce();
        annonce.setId(annonceId);

        Mockito.when(repository.findById(annonceId)).thenReturn(Optional.of(annonce));

        this.mvc.perform(get("/annonce_by_id/{id}", annonceId).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(annonce.getId())));

        verify(repository).findById(annonceId);
    }

    @Test
    void getMes_annonces() throws Exception {
        int utilisateurId = 1;
        List<Annonce> annonces = Arrays.asList(new Annonce(), new Annonce());

        Mockito.when(repository.findByUtilisateur(utilisateurId)).thenReturn(annonces);

        this.mvc.perform(get("/mes_annonces").header("Authorization", "Bearer " + token)
                .header("Utilisateur_id", String.valueOf(utilisateurId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(annonces.size())));

        verify(repository).findByUtilisateur(utilisateurId);
    }

    @Test
    void getMes_gardes() throws Exception {
        int utilisateurGarde = 1;
        List<Annonce> annonces = Arrays.asList(new Annonce(), new Annonce());

        Mockito.when(repository.findUtilisateurGarde(utilisateurGarde)).thenReturn(annonces);

        this.mvc.perform(get("/mes_gardes").header("Authorization", "Bearer " + token)
                .header("Authorization", String.valueOf(utilisateurGarde)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(annonces.size())));

        verify(repository).findUtilisateurGarde(utilisateurGarde);
    }

    @Test
    void postAnnonce() throws Exception {
        final Annonce annonce = new Annonce();
        annonce.setEtat(AnnonceEnum.en_attente);

        Mockito.when(this.repository.save(any(Annonce.class))).thenReturn(annonce);

        this.mvc.perform(post("/annonce/one").header("Authorization", "Bearer " + token)
                .content(asJsonString(annonce))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.etat", is(annonce.getEtat().toString())));

        verify(repository).save(any(Annonce.class));
    }

    @Test
    void putAnnonce() throws Exception {
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
    }

    @Test
    void deleteAnnonce() throws Exception {
        int annonceId = 1;
        doNothing().when(repository).deleteById(annonceId);

        this.mvc.perform(delete("/annonce/{id}", annonceId).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(repository).deleteById(annonceId);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
