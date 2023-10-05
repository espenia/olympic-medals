package com.tdd.grupo5.medallero.entities;

import lombok.Getter;

public class Result {

    @Getter
    private Person athlete;
    @Getter
    private Event event;
    @Getter
    private int standing;
    @Getter
    private int time;   //TODO: a cambiar por una clase con mas info

    public Result(Person athlete, Event event, int standing, int time){

        this.athlete = athlete;
        this.event = event;
        if((standing == 0) || (time == 0)){
            this.standing = 0;
            this.time = 0;
        }
        else{
            this.standing = standing;
            this.time = time;
        }

    }

    //Si el tiempo publicado es Cero, se considera que el atleta no realizo la carrera
    //o que no la termino. Lo mismo si su posicion es Cero
    public boolean athleteFinishedTheRace(){

        return (this.time > 0) && (this.standing > 0);

    }

}
