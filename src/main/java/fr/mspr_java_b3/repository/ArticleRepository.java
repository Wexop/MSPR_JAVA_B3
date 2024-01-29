package fr.mspr_java_b3.repository;

import fr.mspr_java_b3.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "article")

public interface ArticleRepository extends JpaRepository<Article, Integer> {

}