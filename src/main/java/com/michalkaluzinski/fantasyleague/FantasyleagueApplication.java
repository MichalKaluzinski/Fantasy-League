package com.michalkaluzinski.fantasyleague;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class FantasyleagueApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantasyleagueApplication.class, args);
	}

}
