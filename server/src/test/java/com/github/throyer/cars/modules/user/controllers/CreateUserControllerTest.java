package com.github.throyer.cars.modules.user.controllers;

import com.github.throyer.cars.modules.user.dtos.CreateUserData;
import com.github.throyer.cars.modules.user.services.CreateUserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.github.throyer.cars.modules.user.fixtures.UserFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CONFLICT;

@SpringBootTest
public class CreateUserControllerTest {
  @InjectMocks
  private CreateUserController controller;
  
  @Mock
  private CreateUserService service;

  @Test
  void must_create_user_successfully() {
    var data = new CreateUserData(
      NAME,
      EMAIL,
      PASSWORD,
      List.of()
    );
    
    when(service.create(any(CreateUserData.class))).thenReturn(user());

    assertDoesNotThrow(() -> controller.create(data));
  }

  @Test
  void cannot_create_user_when_email_has_already_used() {
    var data = new CreateUserData(
      NAME,
      EMAIL,
      PASSWORD,
      List.of("USER")
    );
    
    when(service.create(any(CreateUserData.class))).thenThrow(new ResponseStatusException(CONFLICT));
    
    var exception = assertThrowsExactly(ResponseStatusException.class, () -> controller.create(data));

    assertTrue(exception.getStatusCode().isSameCodeAs(CONFLICT));
  }
}
