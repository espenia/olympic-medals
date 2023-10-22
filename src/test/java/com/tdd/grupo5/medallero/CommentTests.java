package com.tdd.grupo5.medallero;

import com.tdd.grupo5.medallero.entities.Comment;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.entities.EventType;
import com.tdd.grupo5.medallero.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentTests {

    EventType marathon = new EventType("Marathon", 100);
    Event event = new Event(marathon);
    Person participant = new Person("Michael Phelps", 1985L);
    Comment comment = new Comment(participant, event);

    @Test
    void test01CreatedCommentIsEmpty() {
        assert comment.getComment().isEmpty();
    }

    @Test
    void test02CreatedCommentIsAssignedToAPerson() {
        assert comment.getPerson() == participant;
    }

    @Test
    void test03CreatedCommentIsAssignedToAnEvent() {
        assert comment.getEvent_from() == event;
    }

    @Test
    void test04WritingACommentHasToHaveAtLeastOneCharacter() {
        comment.writeComment(" ");
        assert comment.getComment().isEmpty();
    }

    @Test
    void test05TryingToRewriteACommentDoesNotChangePreviousComment() {
        comment.writeComment("this is a comment");
        comment.writeComment("updated comment");
        assert comment.getComment().contentEquals("this is a comment");
    }

}
