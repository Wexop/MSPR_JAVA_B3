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
    @Query("UPDATE Proposition p SET p.etat = fr.mspr_java_b3.entities.PropositionEnum.refuse WHERE p.annonce.id = ?1 AND p.etat != fr.mspr_java_b3.entities.PropositionEnum.valide")
    void updatePropositionByAnnonceId(int annonceId);

}
