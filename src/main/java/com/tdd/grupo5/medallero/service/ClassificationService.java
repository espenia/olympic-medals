package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.ClassificationLookupDTO;
import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.entities.Athlete;
import com.tdd.grupo5.medallero.entities.Classification;
import com.tdd.grupo5.medallero.exception.NotFoundException;
import com.tdd.grupo5.medallero.repositories.AthleteRepository;
import com.tdd.grupo5.medallero.repositories.ClassificationRepository;
import com.tdd.grupo5.medallero.repositories.ClassificationRepositoryCustom;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClassificationService {

  private final ClassificationRepository repository;
  private final ClassificationRepositoryCustom repositoryCustom;
  private final AthleteRepository athleteRepository;

  public ClassificationService(
      ClassificationRepository repo,
      ClassificationRepositoryCustom repositoryCustom1,
      AthleteRepository athleteRepository) {

    this.repository = repo;
    this.repositoryCustom = repositoryCustom1;
    this.athleteRepository = athleteRepository;
  }

  public ClassificationLookupDTO search(
      String eventName, String athleteFirstName, String athleteLastName, Long userId) {

    List<Classification> classifications =
        repositoryCustom.search(eventName, athleteFirstName, athleteLastName, userId);

    return new ClassificationLookupDTO(
        classifications.stream().map(Classification::convertToDTO).toList());
  }

  public Classification acceptClassification(Long id, UserDTO user) {
    Athlete athlete = athleteRepository.getAthleteByUserName(user.getUserName());
    Classification classification;
    try {
      classification = repository.findById(id).get();
    } catch (Exception e) {
      throw new NotFoundException("Classification not found");
    }
    classification.setAthlete(athlete);
    return repository.save(classification);
  }

  public void rejectClassification(Long id) {
    Classification classification;
    try {
      classification = repository.findById(id).get();
    } catch (Exception e) {
      throw new NotFoundException("Classification not found");
    }
    repository.delete(classification);
  }
}
