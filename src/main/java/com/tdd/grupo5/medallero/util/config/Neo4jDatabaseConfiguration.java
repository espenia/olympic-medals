package com.tdd.grupo5.medallero.util.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


@org.springframework.context.annotation.Configuration
public class Neo4jDatabaseConfiguration {
    @Value("${spring.neo4j.uri}")
    private String uri;
    @Value("${spring.neo4j.authentication.username}")
    private String username;
    @Value("${spring.neo4j.authentication.password}")
    private String password;

    @Bean
    public org.neo4j.ogm.config.Configuration configuration(){
        return new org.neo4j.ogm.config.Configuration.Builder()
                .uri(uri)
                .credentials(username, password)
                .build();
    }
}
