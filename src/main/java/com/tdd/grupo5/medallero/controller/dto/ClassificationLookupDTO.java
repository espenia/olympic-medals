package com.tdd.grupo5.medallero.controller.dto;

import java.util.List;

public class ClassificationLookupDTO extends LookupDTO<SearchClassificationDTO> {

  public ClassificationLookupDTO() {
    super();
  }

  public ClassificationLookupDTO(List<SearchClassificationDTO> classifications) {

    super(classifications);
  }
}
