package com.tdd.grupo5.medallero.util.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

  @Value("${spring.mail.host}")
  private String host;

  @Value("${spring.mail.port}")
  private int port;

  @Value("${spring.mail.username}")
  private String username;

  @Value("${spring.mail.password}")
  private String password;

  @Bean
  public JavaMailSender getJavaMailSender() {

    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

    javaMailSender.setHost(host);
    javaMailSender.setPort(port);
    javaMailSender.setUsername(username);
    javaMailSender.setPassword(password);

    Properties props = javaMailSender.getJavaMailProperties();
    props.put("mail.smtp.starttls.enable", "true");

    return javaMailSender;
  }
}
