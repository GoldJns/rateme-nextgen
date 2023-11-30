package com.app.rateme.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static io.micrometer.core.ipc.http.HttpSender.Method.OPTIONS;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/pois", "/actuator/health" , "/swagger-ui/**" ,"/","/swagger-ui.html","/openapi/openapi.yml","/api/**").permitAll()
				.anyRequest()
				.authenticated());



		return http.build();
	}

}
