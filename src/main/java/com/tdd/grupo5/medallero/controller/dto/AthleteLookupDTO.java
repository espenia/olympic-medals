package com.tdd.grupo5.medallero.controller.dto;

import java.util.List;

public class AthleteLookupDTO extends LookupDTO<AthleteDTO> {
  public AthleteLookupDTO() {
    super();
  }

  public AthleteLookupDTO(List<AthleteDTO> athletes) {
    super(athletes);
  }
}
