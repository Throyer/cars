package com.github.throyer.cars.modules.user.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserData {
  @Schema(example = "fulano")
  @NotEmpty(message = "name is a required field")
  private String name;

  @Schema(example = "fulano.email@example.com")
  @NotEmpty(message = "name is a required field")
  @Email(message = "provide a valid email")
  private String email;

  @Schema(example = "veryStrongPassword123")
  @Size(min = 8, max = 100)
  private String password;

  @Schema(example = "[\"USER\"]")
  @NotEmpty(message = "roles cannot be empty")
  private List<String> roles;

  public CreateUserData(String name, String email, String password, List<String> roles) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }
}
