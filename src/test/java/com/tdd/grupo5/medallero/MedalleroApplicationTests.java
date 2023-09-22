package com.tdd.grupo5.medallero;

import com.tdd.grupo5.medallero.entities.Person;
import com.tdd.grupo5.medallero.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedalleroApplicationTests {


	@Autowired
	private PersonRepository repository;

	@Test
	void contextLoads() {
		repository.save(new Person("Michael Phelps", 1985L));
		Person person = repository.findByName("Michael Phelps");
		assert person.getId() != null;
		assert person.getName().equals("Michael Phelps");
		assert person.getBorn().equals(1985L);
	}

}
