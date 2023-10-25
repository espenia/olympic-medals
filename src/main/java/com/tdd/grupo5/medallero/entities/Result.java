package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.entities.Time;
import lombok.Getter;

public class Result {

    @Getter
    private Athlete athlete;
    @Getter
    private Event event;
    @Getter
    private int standing;
    private Time time;

    public Result(Athlete athlete, Event event, int standing, Time time){

        this.athlete = athlete;
        this.event = event;
        if((standing == 0) || (time.isNull())){
            this.standing = 0;
            this.time = new Time(0, 0, 0);
        }
        else{
            this.standing = standing;
            this.time = time;
        }

    }

    //Si el tiempo publicado es Cero, se considera que el atleta no realizo la carrera
    //o que no la termino. Lo mismo si su posicion es Cero
    public boolean athleteFinishedTheRace(){

        return (!this.time.isNull()) && (this.standing > 0);

    }

}
