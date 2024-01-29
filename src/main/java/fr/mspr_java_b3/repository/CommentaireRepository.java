package fr.mspr_java_b3.repository;

import org.aspectj.bridge.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "commentaire")

public interface CommentaireRepository extends JpaRepository<Message, Integer> {

}
