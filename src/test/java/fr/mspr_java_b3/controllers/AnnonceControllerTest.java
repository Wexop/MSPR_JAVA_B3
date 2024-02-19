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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AnnonceController.class)
class AnnonceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AnnonceRepository repository;

    @Test
    void getAnnonce_attente() throws  Exception {
        final Annonce annonce = new Annonce();
        annonce.setEtat(AnnonceEnum.en_attente);

        final List<Annonce> annonces = Arrays.asList(annonce);

        Mockito.when(this.repository.findByEtat(AnnonceEnum.en_attente)).thenReturn(annonces);

        this.mvc.perform(get("/annonce_attente")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].etat", is(annonce.getEtat().toString())));
    }

    @Test
    void postAnnonce() throws  Exception {
        final Annonce annonce = new Annonce();
        annonce.setEtat(AnnonceEnum.en_attente);

        Mockito.when(this.repository.save(any(Annonce.class))).thenReturn(annonce);

        this.mvc.perform(post("/annonce/one")
                .content(asJsonString(annonce))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.etat", is(annonce.getEtat().toString())));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
