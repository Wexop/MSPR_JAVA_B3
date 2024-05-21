package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "proposition")

public interface PropositionRepository extends JpaRepository<Proposition, Integer> {

    List<Proposition> findPropositionByAnnonce_Id(int id);

}
