package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Bibliotheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "bibliotheque")

public interface BibliothequeRepository extends JpaRepository<Bibliotheque, Integer> {

    @Query("SELECT b FROM Bibliotheque b JOIN FETCH Utilisateur u on u.id = b.utilisateur.id WHERE u.id = ?1")
    List<Bibliotheque> getBibliothequeByUtilisateur(int id);

}
