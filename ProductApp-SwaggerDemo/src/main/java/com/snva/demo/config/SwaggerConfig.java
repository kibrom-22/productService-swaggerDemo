package com.snva.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;

@Configuration                 //http://localhost:8080/swagger-ui.html  (To Test All The APIs )
@EnableSwagger2
public class SwaggerConfig {
	
	 @Bean
	    public Docket atividadeApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.snva.demo"))
	                .paths(regex("/api/products.*"))
	                .build()
	                .apiInfo(metaInfo());
	    }

	    private ApiInfo metaInfo() {

	        ApiInfo apiInfo = new ApiInfo(
	                "Product API REST",
	                "Sample Documentation using SWAGGER2 for Product servise.",
	                "1.0",
	                "Terms of Service",
	                new Contact("Kibrom ", "www.snva.com",
	                        " "),
	                "Apache License Version 2.0",
	                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
	        );

	        return apiInfo;
	    }
	
}
