package fr.mspr_java_b3.services;

import fr.mspr_java_b3.controllers.mapper.PropositionMapper;
import fr.mspr_java_b3.dto.PropositionGetDTO;
import fr.mspr_java_b3.dto.PropositionPatchDTO;
import fr.mspr_java_b3.dto.PropositionPostDTO;
import fr.mspr_java_b3.entities.Proposition;
import fr.mspr_java_b3.entities.PropositionEnum;
import fr.mspr_java_b3.repository.AnnonceRepository;
import fr.mspr_java_b3.repository.PropositionRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropositionService {

    private final PropositionRepository propositionRepository;
    private final PropositionMapper propositionMapper;
    private final AnnonceRepository annonceRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final AnnonceService annonceService;

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
        proposition.setDate(LocalDateTime.now());
        proposition.setAnnonce(annonceRepository.getReferenceById(annonce_id));
        proposition.setUtilisateur(utilisateurRepository.getReferenceById(Integer.parseInt(authorizationHeader)));
        proposition.setEtat(PropositionEnum.en_attente);
        proposition = propositionRepository.save(proposition);
        return propositionMapper.toPropositionGetDTO(proposition);
    }

    public PropositionGetDTO patchProposition(PropositionPatchDTO dto, int proposition_id, String authorizationHeader) {

        Proposition originalProposition = this.propositionRepository.getReferenceById(proposition_id);

        Proposition entity = propositionMapper.toPropositionPatch(dto, originalProposition);
        entity.setId(proposition_id);
        entity.setAnnonce(annonceRepository.getReferenceById(entity.getAnnonce().getId()));
        Proposition saved = propositionRepository.save(entity);

        if (entity.getEtat() == PropositionEnum.valide) {
            int annonceId = entity.getAnnonce().getId();
            annonceService.SetAnnonceAccepted(annonceId, originalProposition.getUtilisateur());
            propositionRepository.updatePropositionByAnnonceId(annonceId);
        }

        return propositionMapper.toPropositionGetDTO(saved);
    }
}
