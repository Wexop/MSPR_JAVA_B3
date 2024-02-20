package fr.mspr_java_b3.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mspr_java_b3.entities.Plante;
import fr.mspr_java_b3.repository.PlanteRepository;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlanteController.class)
class PlanteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlanteRepository repository;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    @Test
    void getMes_plantes() throws Exception {
        int utilisateurId = 1;
        List<Plante> plantes = Arrays.asList(new Plante(), new Plante());

        Mockito.when(repository.findByUtilisateur(utilisateurId)).thenReturn(plantes);

        this.mvc.perform(get("/mes_plantes")
                .header("Utilisateur_id", String.valueOf(utilisateurId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(plantes.size())));
    }

    @Test
    void postPlante() throws Exception {
        Plante plante = new Plante();

        Mockito.when(repository.save(any(Plante.class))).thenReturn(plante);

        this.mvc.perform(post("/plante/one")
                .content(asJsonString(plante))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void putPlante() throws Exception {
        int planteId = 1;
        Plante existingPlante = new Plante();
        existingPlante.setId(planteId);
        existingPlante.setEspece("Old Espece");
        existingPlante.setImage_url("Old url");

        PutPlanteRequest putPlanteRequest = new PutPlanteRequest();
        putPlanteRequest.setEspece("New espece");
        putPlanteRequest.setImage_url("New url");

        Mockito.when(repository.getReferenceById(planteId)).thenReturn(existingPlante);
        Mockito.when(repository.save(any(Plante.class))).thenAnswer(e -> e.getArgument(0));

        this.mvc.perform(put("/plante/{id}", planteId)
                .content(asJsonString(putPlanteRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(planteId))
                .andExpect(jsonPath("$.espece").value(putPlanteRequest.getEspece()))
                .andExpect(jsonPath("$.image_url").value(putPlanteRequest.getImage_url()));

        verify(repository).getReferenceById(planteId);
        verify(repository).save(existingPlante);
    }

    @Test
    void deletePlante() throws Exception {
        int planteId = 1;
        doNothing().when(repository).deleteById(planteId);

        this.mvc.perform(delete("/plante/{id}", planteId))
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

    static class PutPlanteRequest {
        private String espece;
        private String image_url;

        public String getEspece() {
            return espece;
        }

        public void setEspece(String espece) {
            this.espece = espece;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
