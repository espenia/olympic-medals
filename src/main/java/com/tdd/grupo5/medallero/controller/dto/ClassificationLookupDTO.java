package com.tdd.grupo5.medallero.controller.dto;

import java.util.List;

public class ClassificationLookupDTO extends LookupDTO<ClassificationDTO> {

  public ClassificationLookupDTO(List<ClassificationDTO> classifications) {

    super(classifications);
  }
}
