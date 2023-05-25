package com.github.throyer.cars.modules.user.fixtures;

import com.github.throyer.cars.modules.role.model.Role;
import com.github.throyer.cars.modules.user.models.User;

import java.util.List;

public class UserFixture {
  public static final Long ID = 1237L;
  public static final String NAME = "Fulano da silva";
  public static final String EMAIL = "fulano.email@example.com";
  public static final String PASSWORD = "veryStrongPassword123";
  
  public static User user() {
    return user(List.of(new Role("USER")));
  }

  public static User user(List<Role> roles) {
    return new User(ID, NAME, EMAIL, PASSWORD, roles);
  }
}
