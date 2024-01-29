package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Reponse_annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "reponseAnnonce")

public interface ReponseAnnonceRepository extends JpaRepository<Reponse_annonce, Integer> {

}
