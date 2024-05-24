package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.controllers.requests_body.PostAnnonceMessage;
import fr.mspr_java_b3.entities.AnnonceMessage;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.AnnonceMessageRepository;
import fr.mspr_java_b3.repository.AnnonceRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearer")
public class AnnonceMessageControllerAChanger {

    private final AnnonceMessageRepository repository;
    private final AnnonceRepository annonceRepository;

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    private MessageSendingOperations<String> wsTemplate;


    AnnonceMessageControllerAChanger(AnnonceMessageRepository repository, AnnonceRepository annonceRepository, UtilisateurRepository utilisateurRepository) {
        this.repository = repository;
        this.annonceRepository = annonceRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/message/annonce/{id}")
    List<AnnonceMessage> getMessageAnnonce(@PathVariable int id) {
        return repository.findByAnnonce(id);
    }

    @PostMapping("/message/annonce/{id}")
    AnnonceMessage postMessageAnnonce(@PathVariable int id, @RequestBody PostAnnonceMessage annonce, @RequestAttribute(value = "Utilisateur_id") String authorizationValue) {

        var annonceMessage = new AnnonceMessage();

        annonceMessage.setMessage(annonce.getMessage());
        annonceMessage.setImage_url(annonce.getImage_url());
        annonceMessage.setDate(LocalDateTime.now());

        var annonceFound = annonceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas d'annonce avec l'id " + id));
        if (annonceFound != null) {
            annonceMessage.setAnnonce(annonceFound);
        }

        Utilisateur utilisateur = utilisateurRepository.findById(Integer.parseInt(authorizationValue)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvale"));
        if (utilisateur != null) {
            annonceMessage.setUtilisateur(utilisateur);
        }
        this.wsTemplate.convertAndSend("/newMessage/annonce/"+ id, annonceMessage);
        return repository.save(annonceMessage);
    }

    @DeleteMapping("/message/annonce/{id}")
    boolean delete(@PathVariable(name = "id") Integer id) {

        try {
            repository.deleteById(id);
            return true;
        } catch (Error error) {
            return false;
        }
    }
}
