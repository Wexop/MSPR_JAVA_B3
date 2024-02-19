package fr.mspr_java_b3.security;

import fr.mspr_java_b3.entities.*;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.setExposeRepositoryMethodsByDefault(false);
        config.exposeIdsFor(Utilisateur.class, Adresse.class, Article.class, Annonce.class, AnnonceMessage.class, Bibliotheque.class, Commentaire.class, Proposition.class, Plante.class);
    }
}