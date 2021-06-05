package com.home.organizer.config;

import com.home.organizer.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;

@Configuration
public class DataRestConfiguration implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] allowedOrigins;

    private EntityManager entityManager;

    @Autowired
    public DataRestConfiguration(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry corsRegistry) {

        HttpMethod[] theUnsupportedActions = {  HttpMethod.PUT,
                                                HttpMethod.POST,
                                                HttpMethod.DELETE,
                                                HttpMethod.PATCH};

        disableHttpMethods(Note.class, config, theUnsupportedActions);

        corsRegistry.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrigins);

    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }
}
