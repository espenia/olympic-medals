package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MedalTests {

    String event_type_name = "Marathon";
    EventType marathon = new EventType(event_type_name, 2);
    Event event = new Event(marathon);
    Person participant = new Person("Michael Phelps", 1985L);
    Time event_time = new Time(1, 15, 56);
    Result result_from_event = new Result(participant, event, 2, event_time);
    //Person other_participant = new Person("Lionel Messi", 1998L);
    Medal medal = new Medal(participant, event);

    @Test
    void test01CreatedMedalHasToBeAssignedToOnePerson() {

        assert medal.getAthlete() == participant;

    }

    @Test
    void test02CreatedMedalHasToBeAssignedToOneEvent() {

        assert medal.getEvent() == event;

    }

    @Test
    void test03AMedalAssignedToAPersonWhoFinishedAnEventHasAStandingGreaterThanZero() {

        participant.addResult(result_from_event);
        medal.updateStanding(result_from_event.getEvent());
        assert medal.getStanding() > 0;

    }

    @Test
    void test04AMedalAssignedToAPersonWhoHasNotFinishedAnEventHasAStandingEqualToZero() {

        medal.updateStanding(result_from_event.getEvent());
        assert medal.getStanding() == 0;

    }

}
