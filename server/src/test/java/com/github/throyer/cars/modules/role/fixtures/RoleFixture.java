package com.github.throyer.cars.modules.role.fixtures;

import com.github.throyer.cars.modules.role.model.Role;

import java.util.List;

public class RoleFixture {
  public static List<Role> roles() {
    return List.of(
      new Role("USER"),
      new Role("MANAGER"),
      new Role("ADM")
    );
  }
}
