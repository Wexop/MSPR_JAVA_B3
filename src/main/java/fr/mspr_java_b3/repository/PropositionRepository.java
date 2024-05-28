package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(path = "proposition")

public interface PropositionRepository extends JpaRepository<Proposition, Integer> {

    List<Proposition> findPropositionByAnnonce_Id(int id);

    @Modifying
    @Transactional
    @Query("UPDATE FROM Proposition p SET p.etat = 'refuse' WHERE p.annonce.id = ?1 AND p.etat != 'valide'")
    void refusePropositionsFromAnnonce(int annonceId);

}
