package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MedalTests {

    EventType marathon = new EventType("Marathon", 2);
    Event event = new Event(marathon);
    Athlete athlete = new Athlete();
    Time event_time = new Time(1, 15, 56);
    Result result_from_event = new Result(athlete, event, 2, event_time);
    //Person other_participant = new Person("Lionel Messi", 1998L);
    Medal medal = new Medal(athlete, event);

    @Test
    void test01CreatedMedalHasToBeAssignedToOnePerson() {

        assert medal.getAthlete() == athlete;

    }

    @Test
    void test02CreatedMedalHasToBeAssignedToOneEvent() {

        assert medal.getEvent() == event;

    }

    @Test
    void test03AMedalAssignedToAPersonWhoFinishedAnEventHasAStandingGreaterThanZero() {

        athlete.addResult(result_from_event);
        medal.updateStanding(result_from_event.getEvent());
        assert medal.getStanding() > 0;

    }

    @Test
    void test04AMedalAssignedToAPersonWhoHasNotFinishedAnEventHasAStandingEqualToZero() {

        medal.updateStanding(result_from_event.getEvent());
        assert medal.getStanding() == 0;

    }

}
