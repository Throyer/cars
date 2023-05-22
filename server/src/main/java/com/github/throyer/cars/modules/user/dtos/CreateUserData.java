package com.github.throyer.cars.modules.user.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateUserData {
  @Schema(example = "fulano", required = true)
  @NotEmpty(message = "name is a required field")
  private String name;

  @Schema(example = "fulano.email@example.com", required = true)
  @NotEmpty(message = "name is a required field")
  @Email(message = "provide a valid email")
  private String email;

  @Schema(example = "veryStrongPassword123", required = true)
  @Size(min = 8, max = 100)
  private String password;

  @Schema(example = "[\"USER\"]", required = true)
  @NotEmpty(message = "roles cannot be empty")
  private List<String> roles;
}
