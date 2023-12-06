package com.app.rateme.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {

		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}

	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests((authorize) -> authorize
			//.requestMatchers("/auth/**").permitAll()
			//.requestMatchers("/pois", "/actuator/health" , "/swagger-ui/**" ,"/","/swagger-ui.html","/openapi/openapi.yml","/api/**").permitAll()
			//.anyRequest().authenticated()
			.anyRequest().permitAll()
			
			
		)
		/* 
		.logout(logout -> logout
            .logoutUrl("/logout")
            .addLogoutHandler((request, response, auth) -> {
                for (Cookie cookie : request.getCookies()) {
                    String cookieName = cookie.getName();
                    Cookie cookieToDelete = new Cookie(cookieName, null);
                    cookieToDelete.setMaxAge(0);
                    response.addCookie(cookieToDelete);
                }
            })
          )
		  */

		
		
		

		;
		

		return http.build();

		
	}

}
