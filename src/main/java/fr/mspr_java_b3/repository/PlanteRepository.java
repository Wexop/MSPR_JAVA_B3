package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Plante;
import fr.mspr_java_b3.entities.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "plante")

public interface PlanteRepository extends JpaRepository<Plante, Integer> {

}
