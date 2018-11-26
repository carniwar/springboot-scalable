package com.github.carniwar.springboot.scalable.core.test;

import com.github.carniwar.springboot.scalable.core.CoreConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

/**
 * Configure package scan for entity package and initialize JPA EntityManager on test scope.
 */
@DataJpaTest
@EnableAutoConfiguration
@ComponentScan(basePackages = CoreConfig.BASE_PACKAGE)
public class CoreConfigTest {
}
