package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Bibliotheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "bibliotheque")

public interface BibliothequeRepository extends JpaRepository<Bibliotheque, Integer> {

}
