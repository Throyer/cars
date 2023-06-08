package com.github.throyer.cars.modules.user.services;

import static com.github.throyer.cars.modules.user.fixtures.UserFixture.users;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import com.github.throyer.cars.modules.pagination.Page;
import com.github.throyer.cars.modules.user.repositories.UserRepository;

@SpringBootTest
public class FindAllUsersServiceTest {
  @InjectMocks
  private FindAllUsersService service;

  @Mock
  private UserRepository userRepository;
  
  @Test
  void must_find_users() {
    var total = 1386;
    var size = 8;
    var page = 6;
    var content = users(size);
    
    when(userRepository.findAllFetchRoles(any(Pageable.class)))
      .thenReturn(Page.of(content, page, size, 1386L));

    var result = assertDoesNotThrow(() -> service.find(page, size));
    
    assertEquals(page, result.getPage());
    assertEquals(size, result.getSize());
    assertEquals(total, result.getTotalElements());
    assertEquals(size, result.getContent().size());
  }
}
