package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.entities.EventType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventTests {

    EventType marathon = new EventType("Marathon", 2);
    Event event = new Event(marathon);
    Athlete athlete = new Athlete();
    Athlete athlete2 = new Athlete();
    Athlete athlete3 = new Athlete();

    @Test
    void test01CreatedEventHasZeroParticipants() {
        assert event.getNumberOfParticipants() == 0;
    }

    @Test
    void test02AddingAParticipantUpdatesTheNumberOfParticipantsSubscribed() {
        event.addParticipant(athlete);
        assert event.getNumberOfParticipants() == 1;
    }

    @Test
    void test03CreatingAnEventGivesItAnOpenStatus() {
        assert event.isOpen();
    }

    @Test
    void test04EndingAnEventChangesItsOpenStatus() {
        event.finishEvent();
        assert !event.isOpen();
    }

    @Test
    void test05AddingAParticipantToAClosedEventDoesntChangeTheNumberOfParticipants() {
        event.finishEvent();
        event.addParticipant(athlete);
        assert event.getNumberOfParticipants() == 0;
    }

    @Test
    void test06ListOfParticipantsIsNotNullAfterAddingANewParticipant() {
        event.addParticipant(athlete);
        assert !event.getParticipants().isEmpty();
    }

    @Test
    void test07AddingAParticipantToAMaxedEventDoesntChangeTheNumberOfParticipants() {
        event.addParticipant(athlete);
        event.addParticipant(athlete2);
        event.addParticipant(athlete3);
        assert event.getNumberOfParticipants() == 2;
    }
    @Test
    void test08CreatedEventHasCorrectEventType() {
        assert event.getEventType() == "Marathon";
    }

}
