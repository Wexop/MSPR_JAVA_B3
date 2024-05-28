package fr.mspr_java_b3.services;

import fr.mspr_java_b3.controllers.mapper.AdresseMapper;
import fr.mspr_java_b3.controllers.mapper.UtilisateurMapper;
import fr.mspr_java_b3.dto.*;
import fr.mspr_java_b3.entities.Adresse;
import fr.mspr_java_b3.entities.Utilisateur;
import fr.mspr_java_b3.repository.AdresseRepository;
import fr.mspr_java_b3.repository.UtilisateurRepository;
import fr.mspr_java_b3.security.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository repository;
    private final AdresseRepository adresseRepository;
    private final AdresseMapper adresseMapper;
    private final UtilisateurMapper utilisateurMapper;

    public boolean checkMdp(Utilisateur user, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, user.getMdp());
    }

    public Utilisateur hashMdp(Utilisateur user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setMdp(passwordEncoder.encode(user.getMdp()));
        return user;
    }

    public AuthDTO loginUser(@RequestBody LoginDTO request) {

        Utilisateur utilisateur = this.repository.getUtilisateurByMail(request.getMail())
                .orElseThrow(() -> new Error("Aucun utilisateur trouvé avec le mail " + request.getMail()));

        if (!checkMdp(utilisateur, request.getMdp())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrect");
        }

        JwtUtil jwtUtil = new JwtUtil();

        String token = jwtUtil.createToken(utilisateur);

        AuthDTO authResponse = new AuthDTO();
        authResponse.setToken(token);
        authResponse.setUser_id(utilisateur.getId());

        return authResponse;
    }

    public AuthDTO registerUser(@RequestBody RegisterDTO request, HttpServletResponse response) throws Exception {

        Optional<Utilisateur> utilisateur = this.repository.getUtilisateurByMail(request.getMail());

        if (utilisateur.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email déjà utilisé");
        }

        if (request.getAdresse() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adresse invalide");
        }

        this.adresseRepository.save(adresseMapper.toAdresse(request.getAdresse()));

        JwtUtil jwtUtil = new JwtUtil();

        Utilisateur userWithHash = hashMdp(utilisateurMapper.toRegisterUtilisateur(request));

        Utilisateur createdUser = this.repository.save(userWithHash);

        String token = jwtUtil.createToken(createdUser);
        AuthDTO authResponse = new AuthDTO();
        authResponse.setToken(token);
        authResponse.setUser_id(createdUser.getId());


        return authResponse;
    }

    public UtilisateurGetDTO getMe(@RequestAttribute(value = "Utilisateur_id") String authorizationHeader) throws Exception {

        Utilisateur utilisateur = this.repository.findById(Integer.parseInt(authorizationHeader))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisteur introuvable"));
        return utilisateurMapper.toUtilisateurGetDTO(utilisateur);
    }

    public Boolean deleteUser(@RequestAttribute(value = "Utilisateur_id") String authorizationHeader, @RequestBody DeleteUtilisateurDTO request) throws Exception {

        try {
            Utilisateur utilisateur = this.repository.findById(Integer.parseInt(authorizationHeader))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id " + Integer.parseInt(authorizationHeader)));

            if (!checkMdp(utilisateur, request.getMdp())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrect");
            }
            this.repository.deleteById(Integer.parseInt(authorizationHeader));
            return true;
        } catch (Error e) {
            return false;
        }
    }

    public Utilisateur putUtilisateur(@RequestBody PutUtilisateurDTO entity, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        Utilisateur initialEntity = repository.getReferenceById(Integer.parseInt(authorizationHeader));

        if (entity.nom != null) initialEntity.setNom(entity.nom);
        if (entity.image_url != null) initialEntity.setImage_url(entity.image_url);
        if (entity.botaniste != null) initialEntity.setBotaniste(entity.botaniste);
        if (entity.adresse != null) {
            Adresse adresse = adresseRepository.findById(initialEntity.getAdresse().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune adresse trouvée avec l'id " + initialEntity.getAdresse().getId()));
            Adresse adresseEntity = new Adresse(adresse.getId(), entity.adresse.getAdresse(), entity.adresse.getLatitude(), entity.adresse.getLongitude());
            initialEntity.setAdresse(adresseEntity);
            adresseRepository.save(adresseEntity);
        }
        return repository.save(initialEntity);

    }

    public boolean putPassword(@RequestBody PutPasswordUserDTO request, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        Utilisateur utilisateur = this.repository.findById(Integer.parseInt(authorizationHeader))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id " + Integer.parseInt(authorizationHeader)));

        if (!checkMdp(utilisateur, request.getMdp())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrect");
        }

        utilisateur.setMdp(request.getNewMdp());

        this.repository.save(utilisateur);

        return true;
    }

    public boolean putMail(@RequestBody PutMailUserDTO request, @RequestAttribute(value = "Utilisateur_id") String authorizationHeader) {

        Utilisateur utilisateur = this.repository.findById(Integer.parseInt(authorizationHeader))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id " + Integer.parseInt(authorizationHeader)));

        if (!checkMdp(utilisateur, request.getMdp())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrect");
        }

        utilisateur.setMail(request.getNewMail());

        this.repository.save(utilisateur);

        return true;
    }
}
