package fr.mspr_java_b3.services;

import fr.mspr_java_b3.controllers.mapper.PlanteMapper;
import fr.mspr_java_b3.dto.PlanteGetDTO;
import fr.mspr_java_b3.dto.PlantePostDTO;
import fr.mspr_java_b3.entities.Plante;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.PlanteRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlanteService {

    private final PlanteRepository planteRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PlanteMapper planteMapper;

    public List<PlanteGetDTO> getMesPlantes(String authorizationHeader) {
        List<Plante> plantes = planteRepository.findByUtilisateur(Integer.parseInt(authorizationHeader));
        return plantes.stream()
                .map(planteMapper::toGetPlanteDTO)
                .collect(Collectors.toList());
    }

    public PlanteGetDTO postPlante(PlanteGetDTO dto, String authorizationHeader) {
        Plante plante = planteMapper.toGetPlante(dto);
        plante.setUtilisateur(utilisateurRepository.getReferenceById(Integer.parseInt(authorizationHeader)));
        plante = planteRepository.save(plante);
        return planteMapper.toGetPlanteDTO(plante);
    }

    public PlanteGetDTO patchPlante(PlantePostDTO dto, Integer id) {
        Plante plante = planteMapper.toPostPlante(dto);
        plante.setId(id);
        Plante saved = planteRepository.save(plante);
        return planteMapper.toGetPlanteDTO(saved);
    }
}
