package com.flightmanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class OpenAPIconfig {

	@Configuration
	public class OpenApiConfig {
		@Bean
		public OpenAPI usersMicroserviceOpenAPI() {
			return new OpenAPI().info(new Info().title("Flight Management System")
					.description("flight management descrption").version("1.0"));
			}
		}
}
