package com.tdd.grupo5.medallero.entities;

import lombok.Getter;

public class Time {

    @Getter
    private int hours;
    @Getter
    private int minutes;
    @Getter
    private int seconds;
    //tal vez agregar "miliseconds"

    public Time(int hours, int minutes, int seconds){

        correctTime(hours, minutes, seconds);

    }

    //Si se setea alguno de los argumentos como un Cero, ese campo NO va a ser cambiado
    //por un valor nuevo
    public void setNewTime(int new_hours, int new_minutes, int new_seconds){

        if(new_hours == 0){
            new_hours = this.hours;
        }
        if(new_minutes == 0){
            new_minutes = this.minutes;
        }
        if(new_seconds == 0){
            new_seconds = this.seconds;
        }

        correctTime(new_hours, new_minutes, new_seconds);

        System.out.printf("[DEBUG] el nuevo tiempo es %d:%d:%d \n",
                this.hours, this.minutes, this.seconds);

    }

    public boolean isNull(){

        return (this.hours == 0 &&
                this.minutes == 0 &&
                this.seconds == 0);

    }

    public void delete(){

        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;

    }

    //Se encarga de corregir el tiempo pasado por argumento, convirtiendo
    //segundos mayor o iguales a 60 en minutos y a los minutos mayor o iguales
    //a 60 en horas
    private void correctTime(int hours, int minutes, int seconds){

        if(seconds > 59){
            minutes += seconds/60;
            seconds = seconds%60;
        }
        if(minutes > 59){
            hours += minutes/60;
            minutes = minutes%60;
        }

        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

    }
}
