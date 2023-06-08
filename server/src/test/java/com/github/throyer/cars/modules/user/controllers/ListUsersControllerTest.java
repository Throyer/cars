package com.github.throyer.cars.modules.user.controllers;

import com.github.throyer.cars.modules.pagination.Page;
import com.github.throyer.cars.modules.user.services.FindAllUsersService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.throyer.cars.modules.user.fixtures.UserFixture.users;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
public class ListUsersControllerTest {
  @InjectMocks
  private ListUsersController controller;

  @Mock
  private FindAllUsersService service;

  @Test
  void must_find_users() {
    var total = 1386;
    var size = 8;
    var page = 6;
    var content = users(size);

    when(service.find(anyInt(), anyInt()))
      .thenReturn(Page.of(content, page, size, 1386L));

    var result = assertDoesNotThrow(() -> controller.index(page, size));

    assertEquals(OK, result.getStatusCode());
    assertEquals(size, requireNonNull(result.getBody()).getSize());
    assertEquals(total, requireNonNull(result.getBody()).getTotalElements());
    assertEquals(size, requireNonNull(result.getBody()).getContent().size());
  }
}
