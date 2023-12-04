package com.app.rateme.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static io.micrometer.core.ipc.http.HttpSender.Method.OPTIONS;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final String[] AUTH_WHITELIST = {
			// -- Swagger UI v2
			"/pois",
			"/swagger-resources",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**",
			// -- Swagger UI v3 (OpenAPI)
			"/v3/api-docs/**",
			"/swagger-ui/**"
			// other public endpoints of your API may be appended to this array
	};

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/pois", "/actuator/health", "/swagger-ui/**", "/", "/swagger-ui.html", "/openapi/openapi.yml",
						"/api/**")
				.permitAll()
				.anyRequest()
				.permitAll());

		return http.build();
	}

}
