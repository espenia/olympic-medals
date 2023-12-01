package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.ClassificationDTO;
import com.tdd.grupo5.medallero.controller.dto.ClassificationLookupDTO;
import com.tdd.grupo5.medallero.entities.Athlete;
import com.tdd.grupo5.medallero.entities.Classification;
import com.tdd.grupo5.medallero.entities.User;
import com.tdd.grupo5.medallero.exception.NotFoundException;
import com.tdd.grupo5.medallero.repositories.AthleteRepository;
import com.tdd.grupo5.medallero.repositories.ClassificationRepository;
import com.tdd.grupo5.medallero.repositories.ClassificationRepositoryCustom;
import java.util.List;
import java.util.stream.Collectors;

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

  public Classification acceptClassification(Long id, User user) {
    Athlete athlete = athleteRepository.getAthleteByUserName(user.getUserName());
    if (athlete == null) {
      throw new NotFoundException("Athlete not found");
    }
    Classification classification;
    try {
      classification = repository.findById(id).get();
    } catch (Exception e) {
      throw new NotFoundException("Classification not found");
    }
    classification.setAthlete(athlete);
    setMedals(classification, athlete);
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

  private void setMedals(Classification classification, Athlete athlete) {
    if (classification.getPosition() == 1) {
      athlete.setGoldMedals(athlete.getGoldMedals() + 1);
    } else if (classification.getPosition() == 2) {
      athlete.setSilverMedals(athlete.getSilverMedals() + 1);
    } else if (classification.getPosition() == 3) {
      athlete.setBronzeMedals(athlete.getBronzeMedals() + 1);
    }
  }

  public List<ClassificationDTO> getClassificationsByAthlete(int athleteId) {
    return repositoryCustom.searchByAthleteId(athleteId)
            .stream()
            .map(Classification::convertToDTO)
            .collect(Collectors.toList());
  }
}
