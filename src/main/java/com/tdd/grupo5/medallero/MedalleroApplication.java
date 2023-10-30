package com.tdd.grupo5.medallero;

import com.tdd.grupo5.medallero.util.config.ScopeUtils;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

import static com.tdd.grupo5.medallero.util.config.ScopeUtils.SCOPE_SUFFIX;


@SpringBootApplication
//@Slf4j
public class MedalleroApplication {

	public static void main(String[] args) {
		ScopeUtils.calculateScopeSuffix();
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, System.getProperty(SCOPE_SUFFIX));
		//log.info("Starting Spring Boot Application with scope: {}", ScopeUtils.getScopeValue());
		SpringApplication.run(MedalleroApplication.class, args);
	}

}
