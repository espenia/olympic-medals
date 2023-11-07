package com.tdd.grupo5.medallero.util.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@org.springframework.context.annotation.Configuration
@Slf4j
public class Neo4jDatabaseConfiguration {
  @Value("${spring.neo4j.uri}")
  private String uri;

  @Value("${spring.neo4j.authentication.username}")
  private String username;

  @Value("${spring.neo4j.authentication.password}")
  private String password;

  @Bean
  @Profile({"local", "prod", "integration_test"})
  public org.neo4j.ogm.config.Configuration configuration() {
    return new org.neo4j.ogm.config.Configuration.Builder()
        .uri(uri)
        .credentials(username, password)
        .build();
  }

  /*@Bean
  public Neo4jConversions neo4jConversions() {
    HashSet<NodeValueConverter<?>> converters = new HashSet<>();
    converters.add(new NodeValueAthleteConverter());
    return new Neo4jConversions(converters);
  }*/
}
