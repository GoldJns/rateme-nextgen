package com.app.rateme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RatemeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatemeApplication.class, args);
	}

	@RestController
	class Hello {

		@RequestMapping
		String hello() {
			return "Hello World";
		}
	}

}
