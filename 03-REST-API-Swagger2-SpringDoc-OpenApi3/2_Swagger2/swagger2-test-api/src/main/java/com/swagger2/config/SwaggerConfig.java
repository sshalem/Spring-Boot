package com.swagger2.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("shabtay")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.swagger2.controller")) 
				.paths(PathSelectors.ant("/project/**"))				
				.build()
				.useDefaultResponseMessages(false)
				.apiInfo(apiShabtayDetails());
	}

	@Bean
	public Docket apiKarin() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("karin")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.swagger2.controller")) 
				.paths(PathSelectors.ant("/project/**"))				
				.build()
				.useDefaultResponseMessages(false)
				.apiInfo(apiKarinDetails());
	}
	
	@Bean
	public Docket apiOdel() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("odel")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.swagger2.controller")) 
				.paths(PathSelectors.ant("/project/**"))				
				.build()
				.useDefaultResponseMessages(false)
				.apiInfo(apiOdelDetails());
	}
	
	private ApiInfo apiShabtayDetails() { 
		return new ApiInfo(
				"Project Author/Book", 
				"Documentation API", 
				"V1.0", 
				"Free to use",
				new Contact("shabtay shalem", "url - NA", "shabtay.shalem@gmail.com"), 
				"API licesnse",
				"license URL",				
				Collections.emptyList());		
	}
	
	private ApiInfo apiKarinDetails() { 
		return new ApiInfoBuilder()
				.title("Project Author/Book")
				.description("Documentation API")
				.version("V1.0")
				.termsOfServiceUrl("free to use")
				.contact(new Contact("Karin shalem", "url - NA", "karin.shalem@gmail.com"))
				.license("API licesnse")
				.licenseUrl("license URL")
				.extensions(Collections.emptyList())
				.build();
	}
	
	private ApiInfo apiOdelDetails() { 
		return new ApiInfoBuilder()
				.title("Project Author/Book")
				.description("Documentation API")
				.version("V1.0")
				.termsOfServiceUrl("free to use")
				.contact(new Contact("Odel shalem", "url - NA", "Odel.shalem@gmail.com"))
				.license("API licesnse")
				.licenseUrl("license URL")
				.extensions(Collections.emptyList())
				.build();
	}
}
