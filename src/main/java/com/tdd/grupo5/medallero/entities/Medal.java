package com.tdd.grupo5.medallero.entities;

import lombok.Getter;

public class Medal {

    @Getter
    private Person athlete;

    public Medal(){
        this.athlete = null;
    }


    public void assignAthlete(Person athlete) {

        this.athlete = athlete;

    }

    public Person getAthlete(){

        return this.athlete;

    }
}
