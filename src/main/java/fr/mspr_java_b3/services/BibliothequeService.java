package fr.mspr_java_b3.services;

import fr.mspr_java_b3.controllers.mapper.BibliothequeMapper;
import fr.mspr_java_b3.dto.BibliothequeGetDTO;
import fr.mspr_java_b3.entities.Bibliotheque;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.BibliothequeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BibliothequeService {

    private final BibliothequeRepository bibliothequeRepository;
    private final BibliothequeMapper bibliothequeMapper;

    public List<BibliothequeGetDTO> getMesBibliotheque(String authorizationHeader) {
        List<Bibliotheque> bibliotheques = bibliothequeRepository.getBibliothequeByUtilisateur(Integer.parseInt(authorizationHeader));
        return bibliotheques.stream()
                .map(bibliothequeMapper::toBibliothequeGetDTO)
                .collect(Collectors.toList());
    }

    public BibliothequeGetDTO postBibliotheque(BibliothequeGetDTO dto, String authorizationHeader) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(Integer.parseInt(authorizationHeader));

        Bibliotheque bibliotheque = bibliothequeMapper.toGetBibliotheque(dto);
        bibliotheque.setUtilisateur(utilisateur);
        bibliotheque = bibliothequeRepository.save(bibliotheque);
        return bibliothequeMapper.toBibliothequeGetDTO(bibliotheque);
    }
}
