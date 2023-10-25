package com.tdd.grupo5.medallero.entities;

import lombok.Getter;

public class Comment {

    @Getter
    private String comment;
    @Getter
    private User user;
    @Getter
    private Event event_from;

    public Comment(User user, Event event){

        this.comment = "";
        this.user = user;
        this.event_from = event;

    }

    public void writeComment(String comment){

        if(!comment.isBlank() && this.comment.isEmpty()){

            this.comment = comment;

        }

    }

}
