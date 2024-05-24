package fr.mspr_java_b3.services;

import fr.mspr_java_b3.controllers.mapper.AnnonceMapper;
import fr.mspr_java_b3.dto.AnnonceGetDTO;
import fr.mspr_java_b3.dto.AnnoncePostDTO;
import fr.mspr_java_b3.entities.Annonce;
import fr.mspr_java_b3.entities.AnnonceEnum;
import fr.mspr_java_b3.repository.AnnonceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AnnonceService {

    private final AnnonceRepository annonceRepository;
    private final AnnonceMapper annonceMapper;

    public AnnonceGetDTO getAnnonceById(int id) {
        return annonceRepository.findById(id)
                .map(annonceMapper::toAnnonceGetDTO)
                .orElseThrow(() -> new Error("Aucune annonce avec l'id" + id));
    }

    public List<AnnonceGetDTO> getAnnonceAttente() {
        List<Annonce> annonceList = annonceRepository.findByEtat(AnnonceEnum.en_attente);
        return annonceList.stream()
                .map(annonceMapper::toAnnonceGetDTO)
                .collect(Collectors.toList());
    }

    public List<AnnonceGetDTO> getAnnonceAide() {
        List<Annonce> annonceList = annonceRepository.findNeedHelp(AnnonceEnum.en_cours);
        return annonceList.stream()
                .map(annonceMapper::toAnnonceGetDTO)
                .collect(Collectors.toList());
    }

    public List<AnnonceGetDTO> getMesAnnonces(String authorizationValue) {
        List<Annonce> annonceList = annonceRepository.findByUtilisateur(Integer.parseInt(authorizationValue));
        return annonceList.stream()
                .map(annonceMapper::toAnnonceGetDTO)
                .collect(Collectors.toList());
    }

    public List<AnnonceGetDTO> getMesGardes(String authorizationValue) {
        List<Annonce> annonceList = annonceRepository.findUtilisateurGarde(Integer.parseInt(authorizationValue));
        return annonceList.stream()
                .map(annonceMapper::toAnnonceGetDTO)
                .collect(Collectors.toList());
    }

    public AnnonceGetDTO postAnnonce(AnnoncePostDTO dto) {
        Annonce annonce = annonceMapper.toPostAnnonce(dto);
        LocalDateTime localDateTime = LocalDateTime.now();
        annonce.setDate_creation(localDateTime);
        annonce = annonceRepository.save(annonce);
        return annonceMapper.toAnnonceGetDTO(annonce);
    }

    public AnnonceGetDTO patchAnnonce(AnnoncePostDTO dto, Integer id) {
        Annonce entity = annonceMapper.toPostAnnonce(dto);
        entity.setId(id);
        Annonce saved = annonceRepository.save(entity);
        return annonceMapper.toAnnonceGetDTO(saved);
    }
}
