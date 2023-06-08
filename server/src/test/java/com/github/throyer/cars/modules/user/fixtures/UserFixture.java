package com.github.throyer.cars.modules.user.fixtures;

import com.github.throyer.cars.modules.role.model.Role;
import com.github.throyer.cars.modules.user.models.User;

import java.util.ArrayList;
import java.util.List;

import static com.github.throyer.cars.modules.role.fixtures.RoleFixture.roles;
import static com.github.throyer.cars.modules.shared.utils.Random.*;

public class UserFixture {

  public static Long id() {
    return between(1, 999).longValue();
  }
  
  public static String name() {
    return FAKER.name().fullName();
  }

  public static String email() {
    return FAKER.internet().safeEmailAddress();
  }

  public static String password() {
    return FAKER.regexify("[a-z]{5,13}[1-9]{1,5}[A-Z]{1,5}[#?!@$%^&*-]{1,5}");
  }

  public static List<User> users(Integer size) {
    List<User> users = new ArrayList<>();
    for (int index = 0; index < size; index++) {
      users.add(user());
    }
    return users;
  }
  
  public static User user() {
    return user(List.of(element(roles())));
  }

  public static User user(List<Role> roles) {
    return new User(id(), name(), email(), password(), roles);
  }

  public static User user(Long id, String name, String email, String password, List<Role> roles) {
    return new User(id, name, email, password, roles);
  }
}
