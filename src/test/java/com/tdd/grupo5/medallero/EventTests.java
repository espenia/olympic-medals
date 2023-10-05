package com.tdd.grupo5.medallero;

import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventTests {

    Event event = new Event();
    Person participant = new Person("Michael Phelps", 1985L);


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

}
