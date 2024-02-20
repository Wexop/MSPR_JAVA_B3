package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Annonce;
import fr.mspr_java_b3.entities.AnnonceEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "annonce")

public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {

    @Query("SELECT a FROM Annonce a JOIN FETCH Utilisateur u ON u.id = a.utilisateur.id WHERE a.etat = ?1")
    List<Annonce> findByEtat(AnnonceEnum etat);

    @Query("SELECT a FROM Annonce a WHERE a.etat = ?1 and a.besoin_aide = true")
    List<Annonce> findNeedHelp(AnnonceEnum etat);

    @Query("SELECT a FROM Annonce a WHERE a.utilisateur.id = ?1")
    List<Annonce> findByUtilisateur(int id);

    @Query("SELECT a FROM Annonce a JOIN FETCH Proposition p on p.annonce.id = a.id WHERE a.utilisateur.id = ?1")
    List<Annonce> findUtilisateurGarde(int id);

    @Query("SELECT a FROM Annonce a JOIN FETCH Utilisateur u on u.id = a.utilisateur.id JOIN FETCH Plante p on p.id = a.plante.id WHERE a.id = ?1")
    Optional<Annonce> findById(int id);

}
