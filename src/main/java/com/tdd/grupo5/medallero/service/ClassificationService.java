package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.ClassificationLookupDTO;
import com.tdd.grupo5.medallero.entities.Classification;
import com.tdd.grupo5.medallero.repositories.ClassificationRepository;
import com.tdd.grupo5.medallero.repositories.ClassificationRepositoryCustom;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClassificationService {

  private final ClassificationRepository repository;
  private final ClassificationRepositoryCustom repositoryCustom;

  public ClassificationService(
      ClassificationRepository repo, ClassificationRepositoryCustom repositoryCustom1) {

    this.repository = repo;
    this.repositoryCustom = repositoryCustom1;
  }

  public ClassificationLookupDTO search(
      String eventName, String athleteFirstName, String athleteLastName, Long userId) {

    List<Classification> classifications =
        repositoryCustom.search(eventName, athleteFirstName, athleteLastName, userId);

    return new ClassificationLookupDTO(
        classifications.stream().map(Classification::convertToDTO).toList());
  }
}
