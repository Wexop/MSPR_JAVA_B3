package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.GardePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "gardePhoto")

public interface GardePhotoRepository extends JpaRepository<GardePhoto, Integer> {

}
