package com.tdd.grupo5.medallero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class MedalleroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedalleroApplication.class, args);
	}

}
