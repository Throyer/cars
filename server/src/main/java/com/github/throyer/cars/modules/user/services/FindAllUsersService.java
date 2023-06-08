package com.github.throyer.cars.modules.user.services;

import com.github.throyer.cars.modules.pagination.Page;
import com.github.throyer.cars.modules.pagination.utils.Pagination;
import com.github.throyer.cars.modules.user.models.User;
import com.github.throyer.cars.modules.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.github.throyer.cars.modules.infra.constants.LoggingConstants.SIMPLE_LOGGING;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
@AllArgsConstructor
public class FindAllUsersService {
  private final UserRepository repository;
  
  public Page<User> find(Integer page, Integer size) {
    log.info(SIMPLE_LOGGING, format("find users. [page: %s, size: %s]", page, size));
    
    var pageable = Pagination.of(ofNullable(page), ofNullable(size));
    var result = repository.findAllFetchRoles(pageable);
    
    log.info(SIMPLE_LOGGING, "user search performed successfully.");
    return result;
  }
}
