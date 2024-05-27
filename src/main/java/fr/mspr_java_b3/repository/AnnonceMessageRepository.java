package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.AnnonceMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(path = "annonceMessage")

public interface AnnonceMessageRepository extends JpaRepository<AnnonceMessage, Integer> {

    @Query("SELECT am FROM AnnonceMessage am WHERE am.annonce.id = ?1")
    List<AnnonceMessage> findByAnnonce(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM AnnonceMessage am WHERE am.date <= ?1")
    void deleteAnnonceMessageByDateBefore(LocalDateTime date);

}
