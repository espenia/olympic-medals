package com.tdd.grupo5.medallero;

import static com.tdd.grupo5.medallero.util.config.ScopeUtils.SCOPE_SUFFIX;

import com.tdd.grupo5.medallero.util.config.ScopeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@Slf4j
@EnableJpaRepositories(basePackages = "com.tdd.grupo5.medallero.repositories")
@EnableNeo4jRepositories(basePackages = "com.tdd.grupo5.medallero.repositories")
@EntityScan(basePackages = "com.tdd.grupo5.medallero.entities")
public class MedalleroApplication {

  public static void main(String[] args) {
    ScopeUtils.calculateScopeSuffix();
    System.setProperty(
        AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, System.getProperty(SCOPE_SUFFIX));
    log.info("Starting Spring Boot Application with scope: {}", ScopeUtils.getScopeValue());
    SpringApplication.run(MedalleroApplication.class, args);
  }
}
