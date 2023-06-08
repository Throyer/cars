package com.github.throyer.cars.modules.user.repositories;

import com.github.throyer.cars.modules.pagination.Page;
import com.github.throyer.cars.modules.user.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  {
  Page<User> findAllFetchRoles(Pageable pageable);
  Optional<User> findOptionalByEmail(String email);
  Boolean existsByEmail(String email);
  User save(User user);
}
