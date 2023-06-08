package com.github.throyer.cars.modules.user.repositories.springdata;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.throyer.cars.modules.user.models.User;

public interface SpringDataUserRepository extends JpaRepository<User, Long> {
  @EntityGraph(attributePaths = "roles", type = FETCH)
  Optional<User> findOptionalByEmail(String email);

  Boolean existsByEmail(String email);
}
