package com.github.throyer.cars.modules.user.services;

import com.github.throyer.cars.modules.role.repositories.RoleRepository;
import com.github.throyer.cars.modules.user.dtos.CreateUserData;
import com.github.throyer.cars.modules.user.models.User;
import com.github.throyer.cars.modules.user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.github.throyer.cars.modules.role.fixtures.RoleFixture.roles;
import static com.github.throyer.cars.modules.shared.utils.Random.element;
import static com.github.throyer.cars.modules.user.fixtures.UserFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CONFLICT;

@SpringBootTest
class CreateUserServiceTest {
  @InjectMocks
  private CreateUserService service;
  
  @Mock
  private UserRepository userRepository;
  
  @Mock
  private RoleRepository roleRepository;
    
  @Test
  void must_create_user_successfully() {
    var role = element(roles());
    var id = id();
    var name = name();
    var email = email();
    var password = password();
    
    var no = false;
    
    var data = new CreateUserData(
      name,
      email,
      password,
      List.of(role.getName())
    );    
    
    var user = user(id, name, email, password, List.of(role));
    
    when(userRepository.existsByEmail(anyString())).thenReturn(no);
    when(roleRepository.findByNameIn(anyList())).thenReturn(List.of(role));
    when(userRepository.save(any(User.class))).thenReturn(user);

    assertDoesNotThrow(() -> service.create(data));
  }

  @Test
  void cannot_create_user_when_email_has_already_used() {
    var role = element(roles());
    var name = name();
    var email = email();
    var password = password();
    
    var yes = true;

    var data = new CreateUserData(
      name,
      email,
      password,
      List.of(role.getName())
    );
                              
    when(userRepository.existsByEmail(anyString())).thenReturn(yes);

    var exception = assertThrowsExactly(ResponseStatusException.class, () -> service.create(data));
    
    assertTrue(exception.getStatusCode().isSameCodeAs(CONFLICT));
  }
}
