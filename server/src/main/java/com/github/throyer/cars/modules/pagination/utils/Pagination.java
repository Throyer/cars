package com.github.throyer.cars.modules.pagination.utils;

import com.github.throyer.cars.modules.infra.constants.LoggingConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import static com.github.throyer.cars.modules.infra.constants.LoggingConstants.SIMPLE_LOGGING;
import static java.lang.String.format;
import static java.util.Objects.isNull;

@Slf4j
public class Pagination {

  private static final int FIRST_PAGE = 0;
  private static final int DEFAULT_SIZE = 10;

  private static final int MIN_SIZE = 1;
  private static final int MAX_SIZE = 100;

  public static Pageable of(Optional<Integer> page, Optional<Integer> size) {
    
    if (page.isEmpty()) {
      log.warn(SIMPLE_LOGGING, format("page number is null, using default value: %s", FIRST_PAGE));
    }

    if (size.isEmpty()) {
      log.warn(SIMPLE_LOGGING, format("page size is null, using default value: %s", DEFAULT_SIZE));
    }
    
    return Pagination.of(page.orElse(FIRST_PAGE), size.orElse(DEFAULT_SIZE));
  }

  public static Pageable of(Integer page, Integer size) {
    if (page < FIRST_PAGE) {
      log.warn(SIMPLE_LOGGING, format("Received page number: [%s], is invalid, setting default value: [%s].", page, FIRST_PAGE));
      page = FIRST_PAGE;
    }

    if (size < MIN_SIZE || size > MAX_SIZE) {
      log.warn(SIMPLE_LOGGING, format("Received page size: [%s], is invalid, setting default value: [%s].", size, DEFAULT_SIZE));
      size = DEFAULT_SIZE;
    }
    return PageRequest.of(page, size);
  }
}