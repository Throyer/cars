package com.github.throyer.cars.modules.user.dtos;

import com.github.throyer.cars.modules.role.model.Role;
import com.github.throyer.cars.modules.user.models.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UserInformation {
  private final String id;
  private final String name;
  private final List<String> roles;

  public UserInformation(User user) {
    this.id = user.getHashId();
    this.name = user.getName();
    this.roles = user.getRoles().stream().map(Role::getAuthority).toList();
  }
}
