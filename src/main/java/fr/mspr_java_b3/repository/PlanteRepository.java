package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Plante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "plante")

public interface PlanteRepository extends JpaRepository<Plante, Integer> {

    @Query("SELECT p FROM Plante p WHERE p.utilisateur.id = ?1")
    List<Plante> findByUtilisateur(int id);
}
