package org.ra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * --> Info Visualizzabile nell'URL http://localhost:[port]/swagger-ui.html
 * 
 **/
@Configuration
@EnableSwagger2
@SwaggerDefinition(
	info = @Info( 
				  title = "persistence-chat-module",
				  version = "0.0.1-SNAPSHOT", 
				  description = "Persistence module for chat application", 
				  contact = @Contact( 
						  			  name = "Roberto Amato", 
				  					  email = "roberto@miamail.com"), 
				  license = @License( 
						  			  name = "Apache 2.0", 
				  					  url = "http://www.apache.org/licenses/LICENSE-2.0")
	
		  ), 
	consumes = { "application/json", "application/xml" },
	produces = { "application/json", "application/xml" }
)
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		DocumentationType swagger2 = DocumentationType.SWAGGER_2;
		Docket docket = new Docket( swagger2 );

		ApiSelectorBuilder apiSelectorBuilder = docket.select();
		apiSelectorBuilder.apis( RequestHandlerSelectors.any() )
						  .paths( PathSelectors.any() )
						  .build();
		
		return docket;
	}
}
