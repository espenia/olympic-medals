package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.Athlete;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {}
