package com.tdd.grupo5.medallero.entities;

import lombok.Getter;

import java.util.*;


public class Event {

    private int number_of_participants;
    @Getter
    private List<Athlete> participants;
    private boolean open_status;
    private EventType eventType;

    public Event(EventType eventType){

        this.number_of_participants = 0;
        this.participants = new ArrayList<>();
        this.open_status = true;
        this.eventType = eventType;


    }

    public int getNumberOfParticipants(){

        return this.number_of_participants;

    }

    //Maybe change for an int function, that returns -1 if failed
    public void addParticipant(Athlete participant){

        if(this.open_status && this.eventType.getMaxNumberOfParticipants() > this.number_of_participants){
            this.participants.add(participant);
            this.number_of_participants++;
             //Here it would return 0
        }
        //Here it would return -1

    }

    //Returns if the event is still active
    public boolean isOpen() {

        return this.open_status;

    }

    public void finishEvent() {

        this.open_status = false;

    }

    public String getEventType() {

        return this.eventType.getName();

    }

}
