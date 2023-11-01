package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.EventDTO;
import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository repository){

        this.eventRepository = repository;

    }

    public EventDTO createEvent(EventDTO eventData){

        Event newEvent =
                new Event(
                        eventData.getName(),
                        eventData.getMaxParticipantCount(),
                        eventData.getEventType());

        this.eventRepository.save(newEvent);
        return eventData;

    }

    public List<EventDTO> getEvent() {

        List<Event> events = this.eventRepository.findAll();

        return convertToDTO(events);

    }

    private List<EventDTO> convertToDTO(List<Event> events){

        List<EventDTO> listOfEvents= new ArrayList<>(events.size());
        for(int i = 0; i < events.size(); i++){

            listOfEvents.add(i, EventDTO.builder().name(events.get(i).getName())
                                .maxParticipantCount(events.get(i).getMaxParticipantCount())
                                .eventType(events.get(i).getEventType()).build());

        }

        return listOfEvents;

    }

}
