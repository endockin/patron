package com.endockin.patron.config;

import com.google.common.base.Predicate;
import static com.google.common.base.Predicates.or;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    
    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("patron-api")
                .apiInfo(apiInfo())
                .select()
                .paths(paths())
                .build();
    }
    
    private Predicate<String> paths() {
        return or(
                regex("/api/.*")
        );
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Patron API Documentation")
                .description("Description")
                .termsOfServiceUrl("http://patron.io")
                .contact("patron")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/")
                .version("1.0")
                .build();
    }
}
