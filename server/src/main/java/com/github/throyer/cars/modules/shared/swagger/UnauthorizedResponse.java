package com.github.throyer.cars.modules.shared.swagger;

import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public interface UnauthorizedResponse {
  @Schema(example = "Not authorized.", requiredMode = REQUIRED)
  String getMessage();

  @Schema(example = "401", requiredMode = REQUIRED)
  Integer getStatus();
}
