package com.tdd.grupo5.medallero.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Node
@Getter
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    private UUID eventID;
    private String name;
    private int maxParticipantCount;
    private String eventType;
    private List<Athlete> listOfParticipants;

    public Event(String eventName, int maxParticipants, String eventType){

        this.name = eventName;
        this.maxParticipantCount = maxParticipants;
        this.eventType = eventType;
        this.listOfParticipants = new ArrayList<>();

    }

    public void addParticipant(Athlete participant){

        if(this.listOfParticipants.size() < this.maxParticipantCount){

            this.listOfParticipants.add(participant);

        }
        else{
            System.out.printf("[WARN] La cantidad de atletas anotados para este evento ya esta agotada");
        }

    }

}
