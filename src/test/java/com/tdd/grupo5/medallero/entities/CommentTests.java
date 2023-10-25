package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.entities.Comment;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.entities.EventType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class CommentTests {

    EventType marathon = new EventType("Marathon", 100);
    Event event = new Event(marathon);
    Role role = Role.Athlete;
    Date birth_date = new Date(1985L);
    User user = new User("Usain_Bolt", "12345", "Usain", "Bolt", birth_date, "usainb@gmail.com", role);
    Comment comment = new Comment(user, event);

    @Test
    void test01CreatedCommentIsEmpty() {
        assert comment.getComment().isEmpty();
    }

    @Test
    void test02CreatedCommentIsAssignedToAPerson() {
        assert comment.getUser() == user;
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
