package com.github.throyer.cars.modules.user.services;

import static com.github.throyer.cars.modules.infra.constants.LoggingConstants.SIMPLE_LOGGING;
import static com.github.throyer.cars.modules.infra.http.Responses.conflict;

import org.springframework.stereotype.Service;

import com.github.throyer.cars.modules.role.repositories.RoleRepository;
import com.github.throyer.cars.modules.user.dtos.CreateUserData;
import com.github.throyer.cars.modules.user.models.User;
import com.github.throyer.cars.modules.user.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class CreateUserService {
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  
  public User create(CreateUserData data) {
    if (userRepository.existsByEmail(data.getEmail())) {
      log.warn(SIMPLE_LOGGING, "could not create user, email already used.");
      throw conflict("email already used.");
    }
    
    var roles = roleRepository.findByNameIn(data.getRoles());
    
    var user = new User(
      data.getName(),
      data.getEmail(),
      data.getPassword(),
      roles
    );
    
    userRepository.save(user);

    log.info(SIMPLE_LOGGING, "new user successfully created.");
    
    return user;
  }
}
