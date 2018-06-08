package com.kaitait.springfox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.time.LocalDate;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackageClasses = {
        FooController.class
})
public class SpringfoxApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringfoxApplication.class, args);
    }

    @Bean
    public Docket fooBarApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("foobar")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kaitait.springfox"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .useDefaultResponseMessages(false);
    }
}
