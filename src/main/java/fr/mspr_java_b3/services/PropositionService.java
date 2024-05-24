package fr.mspr_java_b3.services;

import fr.mspr_java_b3.controllers.mapper.PropositionMapper;
import fr.mspr_java_b3.dto.PropositionGetDTO;
import fr.mspr_java_b3.dto.PropositionPatchDTO;
import fr.mspr_java_b3.dto.PropositionPostDTO;
import fr.mspr_java_b3.entities.Proposition;
import fr.mspr_java_b3.repository.AnnonceRepository;
import fr.mspr_java_b3.repository.PropositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PropositionService {

    private final PropositionRepository propositionRepository;
    private final PropositionMapper propositionMapper;
=
    public PropositionGetDTO getProposition(int id) {
        return propositionRepository.findById(id)
                .map(propositionMapper::toPropositionGetDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de proposition avec l'id " + id));
    }

    public List<PropositionGetDTO> getPropositionsByAnnonce(int annonce_id) {
        List<Proposition> propositionList = propositionRepository.findPropositionByAnnonce_Id(annonce_id);
        return propositionList.stream()
                .map(propositionMapper::toPropositionGetDTO)
                .collect(Collectors.toList());
    }

    public PropositionGetDTO postProposition(PropositionPostDTO dto, int annonce_id, String authorizationHeader) {
        Proposition proposition = propositionMapper.toPropositionPost(dto);
        proposition = propositionRepository.save(proposition);
        return propositionMapper.toPropositionGetDTO(proposition);
    }

    public PropositionGetDTO patchProposition(PropositionPatchDTO dto, int annonce_id, int proposition_id, String authorizationHeader) {
        Proposition entity = propositionMapper.toPropositionPatch(dto);
        // set annonce_id...
        Proposition saved = propositionRepository.save(entity);
        return propositionMapper.toPropositionGetDTO(saved);
    }
}
