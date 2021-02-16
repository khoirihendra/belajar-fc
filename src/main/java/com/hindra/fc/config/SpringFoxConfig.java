package com.hindra.fc.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
            .select()                                  
            .apis(RequestHandlerSelectors.basePackage("com.hindra.fc"))                
            .build()
            .apiInfo(metadata())
            .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token", "Authorization", "Header"))))
            .tags(new Tag("API Web - Modul User", ""))//
            .tags(new Tag("API Mobile - Modul User", ""));                                
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()//
            .title("Fast Charging API")//
            .description("Dokumentasi API untuk aplikasi Fast Charging")
            .version("1.0.0")//
            .license("MIT License").licenseUrl("http://opensource.org/licenses/MIT")//
            .contact(new Contact(null, null, "muhammad.hindratno@bppt.go.id"))//
            .build();
      }
}
