package com.github.throyer.cars.modules.user.repositories.springdata;

import com.github.throyer.cars.modules.user.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
public interface SpringDataUserRepository extends JpaRepository<User, Long> {
  @EntityGraph(attributePaths = "roles", type = FETCH)
  Optional<User> findOptionalByEmail(String email);

  Boolean existsByEmail(String email);
}
