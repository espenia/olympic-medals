package com.tdd.grupo5.medallero.controller.dto;

import java.util.List;

public class ClassificationLookupDTO extends LookupDTO<UnassignedClassificationDTO> {

  public ClassificationLookupDTO() {
    super();
  }

  public ClassificationLookupDTO(List<UnassignedClassificationDTO> classifications) {

    super(classifications);
  }
}
