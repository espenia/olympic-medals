package com.tdd.grupo5.medallero.entities;

import lombok.Getter;

public class Medal {

    @Getter
    private Person athlete;
    @Getter
    private int standing;
    private Event event_from;

    public Medal(Person athlete, Event event){
        this.athlete = athlete;
        this.event_from = event;
        this.standing = athlete.getStandingResultFrom(event);
    }

    public Event getEvent(){

        return this.event_from;

    }

    public void updateStanding(Event event){

        this.standing = athlete.getStandingResultFrom(event);

    }

}
