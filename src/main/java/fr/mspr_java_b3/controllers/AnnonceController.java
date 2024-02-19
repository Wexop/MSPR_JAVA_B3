package fr.mspr_java_b3.controllers;

import fr.mspr_java_b3.entities.Annonce;
import fr.mspr_java_b3.entities.AnnonceEnum;
import fr.mspr_java_b3.repository.AnnonceRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AnnonceController {

    private final AnnonceRepository repository;

    AnnonceController(AnnonceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/annonce_attente")
    List<Annonce> getAnnonce() {
        return repository.findByEtat(AnnonceEnum.en_attente);
    }

    @GetMapping("/annonce_aide")
    List<Annonce> getAnnonceAide() {
        return repository.findNeedHelp(AnnonceEnum.en_cours);
    }

    @GetMapping("/annonce_by_id/{id}")
    Annonce one(@PathVariable int id) {

        return repository.findById(id)
                .orElseThrow(() -> new Error("Aucune annonce avec l'id " + id));
    }

    @GetMapping("/mes_annonces")
    List<Annonce> mesAnnonce(@RequestHeader(value = "Utilisateur_id") String authorizationValue) {


        return repository.findByUtilisateur(Integer.parseInt(authorizationValue));
    }

    @GetMapping("/mes_gardes")
    List<Annonce> mesGardes(@RequestHeader(value = "Authorization") String authorizationValue) {
        return repository.findUtilisateurGarde(Integer.parseInt(authorizationValue));
    }

    @PostMapping("/annonce/one")
    Annonce postAnnonce(@RequestBody Annonce body) {

        LocalDateTime localDateTime = LocalDateTime.now();
        body.setDate_creation(localDateTime);

        return repository.save(body);
    }

    @DeleteMapping("/annonce/{id}")
    boolean delete(@PathVariable(name = "id") Integer id) {

        try {
            repository.deleteById(id);
            return true;
        } catch (Error error) {
            return false;
        }
    }


}
