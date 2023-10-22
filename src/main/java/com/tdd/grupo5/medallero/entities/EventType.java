package com.tdd.grupo5.medallero.entities;

import lombok.Getter;

import java.util.*;


public class EventType {

    @Getter
    private String name;

    private int max_number_of_participants;

    public EventType(String name, int max_number_of_participants){

        this.name = name;
        this.max_number_of_participants = max_number_of_participants;

    }

    public int getMaxNumberOfParticipants(){

        return this.max_number_of_participants;

    }

}
