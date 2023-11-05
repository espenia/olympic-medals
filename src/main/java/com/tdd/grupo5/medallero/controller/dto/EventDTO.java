package com.tdd.grupo5.medallero.controller.dto;

import com.tdd.grupo5.medallero.entities.Athlete;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@Builder(toBuilder = true)
public class EventDTO {
    private final String name;
    private final int maxParticipantCount;
    private final String eventType;
    private final String placeOfEvent;
    private final String description;
    private boolean isActive;
    private final Date startingDate;
    private final Date endingDate;
    private List<Athlete> listOfParticipants;
}
