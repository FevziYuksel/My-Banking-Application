package org.banking.mybankingapplication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <a href="http://localhost:8090/swagger-ui/index.html">http://localhost:8090/swagger-ui/index.html</a>
 * <a href="http://localhost:8090/swagger-ui/index.html#/">http://localhost:8090/swagger-ui/index.html#/</a>
 *<a href="https://stackoverflow.com/questions/50545286/spring-boot-swagger-ui-set-jwt-token">https://stackoverflow.com/questions/50545286/spring-boot-swagger-ui-set-jwt-token</a>
 * <a href="https://stackoverflow.com/questions/67279877/jwt-authentication-spring-boot-swagger-ui">https://stackoverflow.com/questions/67279877/jwt-authentication-spring-boot-swagger-ui</a>
 */

/**
 * localhost:8090/swagger-ui/index.html">http://localhost:8090/swagger-ui/index.html
 * Put |Bearer <TOKEN>| Bearer before Token key
 */

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    //    public static final String AUTHORIZATION_HEADER =  "Authorization: Bearer [JWT_TOKEN]"; Type error
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.banking.mybankingapplication"))
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.regex("/api/.*"))
//                .paths(PathSelectors.any())
                .build();
    }


    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Spring Boot Banking REST APIs",
                "Spring Boot Banking REST API Documentation",
                "1",
                "Terms of service",
                new Contact("Fevzi Yüksel", "https://github.com/FevziYuksel", "fevziyuksel1996@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList()
        );
    }


    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }


}