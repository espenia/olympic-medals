package com.tdd.grupo5.medallero;

import com.tdd.grupo5.medallero.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedalleroApplicationTests {

  @Autowired private UserRepository repository;

  /*
  @Test
  void contextLoads() {

  }
  */

}
