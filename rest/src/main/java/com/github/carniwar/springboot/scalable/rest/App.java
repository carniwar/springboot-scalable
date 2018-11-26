package com.github.carniwar.springboot.scalable.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application Main class. Called when package application is in run with fat jar.
 */
@SpringBootApplication(
        scanBasePackages = App.BASE_PACKAGE
)
public class App {

    public static final String BASE_PACKAGE = "com.github.carniwar.springboot.scalable";

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}