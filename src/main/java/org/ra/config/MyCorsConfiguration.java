package org.ra.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class MyCorsConfiguration {
	
	private static final String ALLOWED_HTTP_METHODS = "POST, PUT, GET, OPTIONS, DELETE";
	private static final String ALLOWED_ORIGIN = "*";
	private static final String ALLOWED_HEADER = "*";
	private static final String ALLOWED_METHOD = "*";

	@Bean
	 public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(ALLOWED_ORIGIN);
		configuration.setAllowedMethods(Arrays.asList(ALLOWED_HTTP_METHODS));
        configuration.addAllowedHeader(ALLOWED_HEADER);
        configuration.addAllowedMethod(ALLOWED_METHOD);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}
