package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.ClassificationLookupDTO;
import com.tdd.grupo5.medallero.controller.dto.UnassignedClassificationDTO;
import com.tdd.grupo5.medallero.entities.Classification;
import com.tdd.grupo5.medallero.repositories.ClassificationRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClassificationService {

  private final ClassificationRepository repository;

  public ClassificationService(ClassificationRepository repo) {

    this.repository = repo;
  }

  public ClassificationLookupDTO getUnassignedClassifications() {

    List<Classification> classifications = this.repository.findAll();
    List<UnassignedClassificationDTO> unassignedClassifications =
        classifications.stream()
            .filter(Classification::hasNoAthlete)
            .map(Classification::convertToUnassignedDTO)
            .toList();

    return new ClassificationLookupDTO(unassignedClassifications);
  }
}
