package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "annonce")

public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {

}
