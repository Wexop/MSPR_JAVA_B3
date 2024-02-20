package fr.mspr_java_b3.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mspr_java_b3.entities.Proposition;
import fr.mspr_java_b3.repository.PropositionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PropositionController.class)
class PropositionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PropositionRepository repository;

    @Test
    void getProposition() throws Exception {
        int propositionId = 1;
        Proposition proposition = new Proposition();
        proposition.setId(propositionId);

        Mockito.when(repository.findById(propositionId)).thenReturn(Optional.of(proposition));

        this.mvc.perform(get("/proposition/{id}", propositionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(proposition.getId())));

        verify(repository).findById(propositionId);
    }

    @Test
    void postProposition() throws Exception {
        Proposition proposition = new Proposition();
        proposition.setId(1);

        Mockito.when(repository.save(any(Proposition.class))).thenReturn(proposition);

        this.mvc.perform(post("/proposition")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(proposition)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

        verify(repository).save(any(Proposition.class));
    }

    @Test
    void patchProposition() throws Exception {
        Proposition proposition = new Proposition();
        proposition.setId(1);

        Mockito.when(repository.save(any(Proposition.class))).thenReturn(proposition);

        this.mvc.perform(patch("/proposition")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(proposition)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

        verify(repository).save(any(Proposition.class));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
