package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "topic")

public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
