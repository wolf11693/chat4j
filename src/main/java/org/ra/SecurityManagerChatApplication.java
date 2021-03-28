package org.ra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class SecurityManagerChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityManagerChatApplication.class, args);
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}
