package com.app.rateme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

@SpringBootApplication
public class RatemeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatemeApplication.class, args);
	}

	@RestController
	@Service
	class Hello {
		private ObservationRegistry observationRegistry;

		private Logger logger = LoggerFactory.getLogger(RatemeApplication.class);

		@RequestMapping("/")
		String hello() {
			logger.debug("this is a debug log");
			logger.info("moinsen");
			logger.error("this is exception");
			return Observation
					.createNotStarted("helloService", observationRegistry)
					.observe(() -> this.helloNoObserve());
		}

		String helloNoObserve() {
			return "Hello World NoObserve!";
		}
	}

}
