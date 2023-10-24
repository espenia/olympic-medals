package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonTests {

    String name = "Michael Phelps";
    Person person = new Person(name, 1985L);

    @Test
    void test01CreatedPersonHasCorrectName() {
        assert person.getName() == name;
    }

    @Test
    void test02CreatedPersonHasPrivateSetting() {
        assert !person.isPublic();
    }

    @Test
    void test03PersonCanChangeToPublicSetting() {
        person.setPublic();
        assert person.isPublic();
    }

    @Test
    void test04PersonCanChangeToPrivateSetting() {
        person.setPublic();
        person.setPrivate();
        assert !person.isPublic();
    }
}
