package com.github.carniwar.springboot.scalable.rest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Configure Spring boot subsystem for unit and integration tests.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = App.BASE_PACKAGE)
public class RestConfigTest {
}
