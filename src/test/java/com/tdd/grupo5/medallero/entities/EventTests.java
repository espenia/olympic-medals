package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.entities.EventType;
import com.tdd.grupo5.medallero.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventTests {

    String event_type_name = "Marathon";
    EventType marathon = new EventType(event_type_name, 2);
    Event event = new Event(marathon);
    Person participant = new Person("Michael Phelps", 1985L);
    Person participant2 = new Person("Lionel Messi", 1998L);
    Person participant3 = new Person("Alex Perez", 1977L);

    @Test
    void test01CreatedEventHasZeroParticipants() {
        assert event.getNumberOfParticipants() == 0;
    }

    @Test
    void test02AddingAParticipantUpdatesTheNumberOfParticipantsSubscribed() {
        event.addParticipant(participant);
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
        event.addParticipant(participant);
        assert event.getNumberOfParticipants() == 0;
    }

    @Test
    void test06ListOfParticipantsIsNotNullAfterAddingANewParticipant() {
        event.addParticipant(participant);
        assert !event.getParticipants().isEmpty();
    }

    @Test
    void test07AddingAParticipantToAMaxedEventDoesntChangeTheNumberOfParticipants() {
        event.addParticipant(participant);
        event.addParticipant(participant2);
        event.addParticipant(participant3);
        assert event.getNumberOfParticipants() == 2;
    }
    @Test
    void test08CreatedEventHasCorrectEventType() {
        assert event.getEventType() == event_type_name;
    }

}
