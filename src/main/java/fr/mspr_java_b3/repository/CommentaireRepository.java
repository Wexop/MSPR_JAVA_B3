package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "commentaire")

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {

}
