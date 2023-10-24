package com.tdd.grupo5.medallero.entities;

import lombok.Getter;

public class Comment {

    @Getter
    private String comment;
    @Getter
    private Person person;
    @Getter
    private Event event_from;

    public Comment(Person person, Event event){

        this.comment = "";
        this.person = person;
        this.event_from = event;

    }

    public void writeComment(String comment){

        if(!comment.isBlank() && this.comment.isEmpty()){

            this.comment = comment;

        }

    }

}
