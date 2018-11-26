package com.github.carniwar.springboot.scalable.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API documentation integration with Spring Boot.
 */
@Configuration
@EnableSwagger2
public class ApiConfig {

    @Bean
    public Docket productApiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("diff-api-1")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/v1.*"))
                .build()
                .apiInfo(new ApiInfoBuilder().version("1").title("Diff API").description("Diff API v1").build());
    }

}