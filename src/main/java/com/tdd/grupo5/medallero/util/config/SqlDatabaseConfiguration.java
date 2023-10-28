package com.tdd.grupo5.medallero.util.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class SqlDatabaseConfiguration {
  @Value("${spring.datasource.url}")
  private String url;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Value("${spring.datasource.driver-class-name}")
  private String driverClassName;

  @Bean
  @Primary
  @Profile({"local", "prod", "integration_test"})
  public DataSource dataSource() {
    return DataSourceBuilder.create()
        .username(username)
        .password(password)
        .url(url)
        .driverClassName(driverClassName)
        .build();
  }
}
