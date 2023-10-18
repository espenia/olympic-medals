package com.tdd.grupo5.medallero;

import com.tdd.grupo5.medallero.entities.Medal;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MedalTests {

    Event event = new Event();
    Person participant = new Person("Michael Phelps", 1985L);
    Person other_participant = new Person("Lionel Messi", 1998L);
    Medal medal = new Medal();

    @Test
    void test01CreatedCanOnlyBeAssignedToOnePerson() {
        medal.assignAthlete(participant);
        medal.assignAthlete(other_participant);
        assert medal.getAthlete() == participant;
    }

}
