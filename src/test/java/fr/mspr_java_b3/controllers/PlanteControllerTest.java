package fr.mspr_java_b3.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mspr_java_b3.entities.Plante;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.PlanteRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlanteController.class)
class PlanteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlanteRepository repository;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    String token = new JwtUtilTest().getFakeToken();

    @Test
    void getMes_plantes() throws Exception {
        int utilisateurId = 1;
        List<Plante> plantes = Arrays.asList(new Plante(), new Plante());

        Mockito.when(repository.findByUtilisateur(utilisateurId)).thenReturn(plantes);

        this.mvc.perform(get("/mes_plantes")
                .header("Utilisateur_id", String.valueOf(utilisateurId))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(plantes.size())));
    }

    @Test
    void postPlante() throws Exception {
        Plante plante = new Plante();
        plante.setEspece("test-plante");

        Mockito.when(repository.save(any(Plante.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

        Utilisateur user = new Utilisateur();
        user.setId(1);
        Mockito.when(utilisateurRepository.findById(anyInt())).thenReturn(Optional.of(user));

        this.mvc.perform(post("/plante/one").header("Authorization", "Bearer " + token)
                .content(asJsonString(plante))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

        verify(repository).save(Mockito.any(Plante.class));
    }

    @Test
    void putPlante() throws Exception {
        int planteId = 1;
        Plante plante = new Plante();
        plante.setId(planteId);

        Mockito.when(repository.getReferenceById(planteId)).thenReturn(plante);
        Mockito.when(repository.save(any(Plante.class))).thenAnswer(e -> e.getArgument(0));

        this.mvc.perform(put("/plante/{id}", planteId).header("Authorization", "Bearer " + token)
                .content(asJsonString(plante))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(planteId));

        verify(repository).getReferenceById(planteId);
        verify(repository).save(any(Plante.class));
    }

    @Test
    void deletePlante() throws Exception {
        int planteId = 1;
        doNothing().when(repository).deleteById(planteId);

        this.mvc.perform(delete("/plante/{id}", planteId).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(repository).deleteById(planteId);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
