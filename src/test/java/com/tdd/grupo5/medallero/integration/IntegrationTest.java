package com.tdd.grupo5.medallero.integration;

import com.tdd.grupo5.medallero.MedalleroApplication;
import com.tdd.grupo5.medallero.config.SecurityConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MedalleroApplication.class, SecurityConfiguration.class},
        webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration_test")
public class IntegrationTest {
  protected IntegrationTest() {
    System.setProperty("SCOPE_SUFFIX", "integration_test");
    System.setProperty("SCOPE", "integration_test");
  }
}
