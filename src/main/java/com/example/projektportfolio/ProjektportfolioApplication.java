package com.example.projektportfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjektportfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjektportfolioApplication.class, args);
	}

}
