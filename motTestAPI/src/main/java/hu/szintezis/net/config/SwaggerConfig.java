package hu.szintezis.net.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("hu.szintezis.demo"))
				.paths(PathSelectors.regex("/api.*"))
				.build()
				.apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo() {
	     return new ApiInfoBuilder()
	             .title("API for Backend Tribe entering challenge")
	             .description("Author: Martin Madai. \n"
	                + "This is a demo Spring Boot app for sharing my knowledge of backend technologies and databases")
	             .version("1.0.0")
	             .build();
	 }
	


}
