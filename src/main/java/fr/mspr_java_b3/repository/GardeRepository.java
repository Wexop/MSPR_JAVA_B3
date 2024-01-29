package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Garde;
import fr.mspr_java_b3.entities.GardePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "garde")

public interface GardeRepository extends JpaRepository<Garde, Integer> {

}
