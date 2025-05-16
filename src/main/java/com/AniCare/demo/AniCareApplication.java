package com.AniCare.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.AniCare.demo")
@EntityScan(basePackages = "com.AniCare.demo.entity")
@EnableJpaRepositories(basePackages = "com.AniCare.demo.repository")
public class AniCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(AniCareApplication.class, args);
    }

}
