package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Plante;
import org.aspectj.bridge.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "message")

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
