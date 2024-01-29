package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Annonce;
import fr.mspr_java_b3.entities.AnnonceEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "annonce")

public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {

    @Query("SELECT a FROM Annonce a WHERE a.etat = ?1")
    List<Annonce> findByEtat(AnnonceEnum etat);

    @Query("SELECT a FROM Annonce a WHERE a.etat = ?1 and a.besoin_aide = true")
    List<Annonce> findNeedHelp(AnnonceEnum etat);

}
