package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "adresse")

public interface AdresseRepository extends JpaRepository<Adresse, Integer> {

}
