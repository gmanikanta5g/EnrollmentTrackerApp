package com.health.enroll.enrollmenttracker.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Enabling Swagger API-Docs using Swagger 2.
 * @author gmani
 *
 */
@Configuration
@EnableSwagger2 //  main annotation to enable swagger support
public class SwaggerConfig {

  @Bean
  public Docket postsApi() {
            return new Docket(DocumentationType.SWAGGER_2).groupName("enrollemnt-tracker")
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.health.enroll.enrollmenttracker.controller"))
          .paths(PathSelectors.any())
          .build().apiInfo(apiInfo()); 

  }
  

//  @Override 
//  protected void addResourceHandlers(ResourceHandlerRegistry registry) { 
//    registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
//    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//  }
  
     private Predicate<String> myPaths() {
      return regex("/.*");
  }

  private ApiInfo apiInfo() {
      return new ApiInfoBuilder().title("Enrollment Tracker APIs")
              .description("Enrollment Tracker APIs helps in Enrollee and Dependents registration/updation/deletion, and keeps track of Enrollees ")
              .termsOfServiceUrl("https://testsample.com")
              .license("Simple License")
              .licenseUrl("testsample@gmail.com").version("1.0").build();
  }
}