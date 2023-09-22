package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.entities.Person;
import com.tdd.grupo5.medallero.repositories.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/person/{name}")
    public Person getPerson(@PathVariable String name) {
        return repository.findByName(name);
    }
}
