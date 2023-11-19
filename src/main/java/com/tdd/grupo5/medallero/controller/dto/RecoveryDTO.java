package com.tdd.grupo5.medallero.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class RecoveryDTO {
  @NotBlank private String mail;
  private String redirectUrl;
}
