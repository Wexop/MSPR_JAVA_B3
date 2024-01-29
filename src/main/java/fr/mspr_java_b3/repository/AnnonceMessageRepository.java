package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.AnnonceMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "annonceMessage")

public interface AnnonceMessageRepository extends JpaRepository<AnnonceMessage, Integer> {

}
