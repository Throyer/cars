package com.github.throyer.cars.modules.user.services;

import com.github.throyer.cars.modules.role.repositories.RoleRepository;
import com.github.throyer.cars.modules.user.dtos.CreateUserData;
import com.github.throyer.cars.modules.user.models.User;
import com.github.throyer.cars.modules.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.github.throyer.cars.modules.infra.http.Responses.conflict;

@Service
@AllArgsConstructor
public class CreateUserService {
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  
  public User create(CreateUserData data) {
    if (userRepository.existsByEmail(data.getEmail())) {
      throw conflict("email already used");
    }
    
    var roles = roleRepository.findByNameIn(data.getRoles());
    
    var user = new User(
      data.getName(),
      data.getEmail(),
      data.getPassword(),
      roles
    );
    
    userRepository.save(user);
    
    return user;
  }
}
