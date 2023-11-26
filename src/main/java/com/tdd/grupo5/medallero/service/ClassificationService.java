package com.tdd.grupo5.medallero.service;

import com.tdd.grupo5.medallero.controller.dto.ClassificationLookupDTO;
import com.tdd.grupo5.medallero.controller.dto.SearchClassificationDTO;
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

  public ClassificationLookupDTO getUnassignedClassifications(
      String athleteFirstName, String athleteLastName, String eventName, Long eventId) {

    List<Classification> classifications = this.repository.findAll();
    classifications = classifications.stream().filter(Classification::hasNoAthlete).toList();

    classifications =
        filter(classifications, athleteFirstName, athleteLastName, eventName, eventId);

    List<SearchClassificationDTO> unassignedClassifications = convertToSearchDTO(classifications);

    return new ClassificationLookupDTO(unassignedClassifications);
  }

  public ClassificationLookupDTO getAssignedClassifications(
      String athleteFirstName,
      String athleteLastName,
      Long athleteId,
      String eventName,
      Long eventId) {

    List<Classification> classifications = this.repository.findAll();
    classifications =
        classifications.stream()
            .filter(classification -> classification.getAthlete().getId().equals(athleteId))
            .toList();

    classifications =
        filter(classifications, athleteFirstName, athleteLastName, eventName, eventId);

    List<SearchClassificationDTO> unassignedClassifications = convertToSearchDTO(classifications);

    return new ClassificationLookupDTO(unassignedClassifications);
  }

  private List<Classification> filter(
      List<Classification> classifications,
      String athleteFirstName,
      String athleteLastName,
      String eventName,
      Long eventId) {

    List<Classification> filteredClassifications =
        filterAthleteFirstName(classifications, athleteFirstName);
    filteredClassifications = filterAthleteLastName(filteredClassifications, athleteLastName);
    filteredClassifications = filterEventName(filteredClassifications, eventName);
    filteredClassifications = filterEventId(filteredClassifications, eventId);

    return filteredClassifications;
  }

  private List<Classification> filterAthleteFirstName(
      List<Classification> classifications, String athleteFirstName) {

    if (athleteFirstName != null) {

      return classifications.stream()
          .filter(classification -> classification.getAthleteFirstName().equals(athleteFirstName))
          .toList();
    }

    return classifications;
  }

  private List<Classification> filterAthleteLastName(
      List<Classification> classifications, String athleteLastName) {

    if (athleteLastName != null) {

      return classifications.stream()
          .filter(classification -> classification.getAthleteLastName().equals(athleteLastName))
          .toList();
    }

    return classifications;
  }

  private List<Classification> filterEventId(List<Classification> classifications, Long eventId) {

    if (eventId != null) {

      return classifications.stream()
          .filter(classification -> classification.getEvent().getId().equals(eventId))
          .toList();
    }

    return classifications;
  }

  private List<Classification> filterEventName(
      List<Classification> classifications, String eventName) {

    if (eventName != null) {

      return classifications.stream()
          .filter(classification -> classification.getEvent().getName().equals(eventName))
          .toList();
    }

    return classifications;
  }

  private List<SearchClassificationDTO> convertToSearchDTO(List<Classification> classifications) {

    return classifications.stream().map(Classification::convertToSearchDTO).toList();
  }
}
