package fr.mspr_java_b3.services;

import fr.mspr_java_b3.controllers.mapper.PlanteMapper;
import fr.mspr_java_b3.dto.PlanteGetDTO;
import fr.mspr_java_b3.entities.Plante;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.PlanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlanteService {

    private final PlanteRepository planteRepository;
    private final PlanteMapper planteMapper;

    public List<PlanteGetDTO> getMesPlantes(String authorizationHeader) {
        List<Plante> plantes = planteRepository.findByUtilisateur(Integer.parseInt(authorizationHeader));
        return plantes.stream()
                .map(planteMapper::toPlanteGetDTO)
                .collect(Collectors.toList());
    }

    public PlanteGetDTO postPlante(PlanteGetDTO dto, String authorizationHeader) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(Integer.parseInt(authorizationHeader));

        Plante plante = planteMapper.toGetPlante(dto);
        plante.setUtilisateur(utilisateur);
        plante = planteRepository.save(plante);
        return planteMapper.toPlanteGetDTO(plante);
    }

    public PlanteGetDTO putPlante(PlanteGetDTO dto, Integer id) {
        dto.setId(id);
        Plante plante = planteMapper.toGetPlante(dto);
        Plante saved = planteRepository.save(plante);
        return planteMapper.toPlanteGetDTO(saved);
    }
}
