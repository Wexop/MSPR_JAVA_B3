package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "utilisateur")

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

}
