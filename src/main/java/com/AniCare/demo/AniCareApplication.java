package com.AniCare.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = "com.AniCare")
public class AniCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(AniCareApplication.class, args);
	}

}
