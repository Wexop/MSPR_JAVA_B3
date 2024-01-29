package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.PlanteUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "planteUtilisateur")

public interface PlanteUtilisateurRepository extends JpaRepository<PlanteUtilisateur, Integer> {

}
