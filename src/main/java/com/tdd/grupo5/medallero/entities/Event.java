package com.tdd.grupo5.medallero.entities;

import lombok.Getter;

import java.util.*;


public class Event {

    private int number_of_participants;
    @Getter
    private List<Person> participants;
    private boolean open_status;

    public Event(){

        this.number_of_participants = 0;
        this.participants = new ArrayList<>();
        this.open_status = true;

    }

    public int getNumberOfParticipants(){

        return this.number_of_participants;

    }

    public void addParticipant(Person participant){

        if(this.open_status){
            this.participants.add(participant);
            this.number_of_participants++;
        }

    }

    //Returns if the event is still active
    public boolean isOpen() {

        return this.open_status;

    }

    public void finishEvent() {

        this.open_status = false;

    }

}
