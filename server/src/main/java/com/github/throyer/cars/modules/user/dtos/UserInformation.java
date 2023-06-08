package com.github.throyer.cars.modules.user.dtos;

import com.github.throyer.cars.modules.role.model.Role;
import com.github.throyer.cars.modules.user.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
public class UserInformation {
  @Schema(example = "67gJd8J9YQ", requiredMode = REQUIRED)
  private final String id;

  @Schema(example = "fulano da silva", requiredMode = REQUIRED)
  private final String name;

  @Schema(example = "[\"USER\", \"ADM\"]", requiredMode = REQUIRED)
  private final List<String> roles;

  public UserInformation(User user) {
    this.id = user.getHashId();
    this.name = user.getName();
    this.roles = user.getRoles().stream().map(Role::getAuthority).toList();
  }
}
