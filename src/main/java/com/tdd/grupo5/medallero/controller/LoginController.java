package com.tdd.grupo5.medallero.controller;

import com.tdd.grupo5.medallero.entities.User;
import com.tdd.grupo5.medallero.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserRepository repository;

    public LoginController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/person/{name}")
    public User getPerson(@PathVariable String name) {
        return repository.findByName(name);
    }
}
