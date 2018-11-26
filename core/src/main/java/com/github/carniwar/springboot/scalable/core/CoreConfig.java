package com.github.carniwar.springboot.scalable.core;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configure package scan for entity package and initialize JPA EntityManager.
 */
@Configuration
@EntityScan(basePackages = CoreConfig.ENTITY_PACKAGE)
@EnableJpaRepositories(
        basePackages = CoreConfig.REPOSITORY_PACKAGE
)
public class CoreConfig {

    public static final String BASE_PACKAGE = "com.github.carniwar.springboot.scalable.core";
    public static final String ENTITY_PACKAGE = BASE_PACKAGE + ".domain";
    public static final String REPOSITORY_PACKAGE = BASE_PACKAGE + ".repository";

}
