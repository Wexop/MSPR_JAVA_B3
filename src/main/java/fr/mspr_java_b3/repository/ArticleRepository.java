package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "article")

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Query("SELECT a FROM Article a JOIN FETCH Utilisateur u on u.id = a.utilisateur.id")
    List<Article> findAll();

    @Query("SELECT a FROM Article a JOIN FETCH Utilisateur u on u.id = a.utilisateur.id WHERE a.id = ?1")
    Optional<Article> findById(int id);

}
