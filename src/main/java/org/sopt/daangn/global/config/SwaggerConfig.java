package org.sopt.daangn.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.components(new Components())
				.addServersItem(new Server().url("/"))
				.info(apiInfo());
	}

	private Info apiInfo() {
		return new Info()
				.title("합동세미나 Swagger")
				.description("합동세미나 IOS 3조 당근")
				.version("1.0.0");
	}
}
