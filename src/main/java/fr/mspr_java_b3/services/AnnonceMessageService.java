package fr.mspr_java_b3.services;

import fr.mspr_java_b3.controllers.mapper.AnnonceMessageMapper;
import fr.mspr_java_b3.dto.AnnonceMessageGetDTO;
import fr.mspr_java_b3.dto.AnnonceMessagePostDTO;
import fr.mspr_java_b3.entities.AnnonceMessage;
import fr.mspr_java_b3.repository.AnnonceMessageRepository;
import fr.mspr_java_b3.repository.AnnonceRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AnnonceMessageService {
    private final AnnonceMessageRepository annonceMessageRepository;
    private final AnnonceMessageMapper annonceMessageMapper;
    private final AnnonceRepository annonceRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    private MessageSendingOperations<String> wsTemplate;

    public List<AnnonceMessageGetDTO> getAllMessageByAnnonce(int id_annonce) {
        List<AnnonceMessage> annonceMessageList = annonceMessageRepository.findByAnnonce(id_annonce);
        return annonceMessageList.stream()
                .map(annonceMessageMapper::toAnnonceMessageGetDTO)
                .collect(Collectors.toList());
    }

    public AnnonceMessageGetDTO postMessageAnnonce(int id_annonce, AnnonceMessagePostDTO dto, String authorizationValue) {
        AnnonceMessage annonceMessage = annonceMessageMapper.toAnnonceMessagePost(dto);
        LocalDateTime localDateTime = LocalDateTime.now();
        annonceMessage.setDate(localDateTime);
        annonceMessage.setAnnonce(annonceRepository.getReferenceById(id_annonce));
        annonceMessage.setUtilisateur(utilisateurRepository.getReferenceById(Integer.parseInt(authorizationValue)));
        annonceMessage = annonceMessageRepository.save(annonceMessage);
        this.wsTemplate.convertAndSend("/newMessage/annonce/"+ id_annonce, annonceMessage);
        return annonceMessageMapper.toAnnonceMessageGetDTO(annonceMessage);

    }


}
