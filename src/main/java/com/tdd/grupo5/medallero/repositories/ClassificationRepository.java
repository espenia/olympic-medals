package com.tdd.grupo5.medallero.repositories;

import com.tdd.grupo5.medallero.entities.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationRepository extends JpaRepository<Classification, Long> {}
