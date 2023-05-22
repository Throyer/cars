package com.github.throyer.cars.modules.user.repositories;

import com.github.throyer.cars.modules.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findOptionalByEmail(String email);
  Boolean existsByEmail(String email);
}
