package com.github.throyer.cars.modules.user.repositories;

import com.github.throyer.cars.modules.pagination.Page;
import com.github.throyer.cars.modules.user.models.User;
import com.github.throyer.cars.modules.user.repositories.custom.CustomUserRepository;
import com.github.throyer.cars.modules.user.repositories.springdata.SpringDataUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
  private final SpringDataUserRepository springDataUserRepository;
  private final CustomUserRepository customUserRepository;
  
  @Override
  public Page<User> findAllFetchRoles(Pageable pageable) {
    return customUserRepository.findAllFetchRoles(pageable);
  }

  @Override
  public Optional<User> findOptionalByEmail(String email) {
    return springDataUserRepository.findOptionalByEmail(email);
  }

  @Override
  public Boolean existsByEmail(String email) {
    return springDataUserRepository.existsByEmail(email);
  }

  @Override
  public User save(User user) {
    return springDataUserRepository.save(user);
  }
}
