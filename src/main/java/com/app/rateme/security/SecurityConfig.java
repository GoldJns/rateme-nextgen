package com.app.rateme.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JWTAuthenticationFilter requestFilter;

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
		return http.cors().and().csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/auth/**", "/pois/**", "/rating/**", "/actuator/health", "/swagger-ui/**",
				"/", "/swagger-ui.html", "/openapi/openapi.yml", "/api/**", "/user/**")
				.permitAll()
				.and()
				.authorizeHttpRequests().requestMatchers("/api/**")
				.authenticated().and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration config = new CorsConfiguration();

		config.setAllowedOrigins(Arrays.asList("http://localhost:8082"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "token"));

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
