package com.github.throyer.cars.modules.shared.utils;

import com.github.throyer.cars.modules.shared.ids.StringIds;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
public class IdUtils {
  private static StringIds stringIds;

  public IdUtils(StringIds stringIds) {
    IdUtils.stringIds = stringIds;
  }
  
  public static String encode(Long id) {
    return requireNonNull(stringIds, "string id encoder is null")
      .encode(id);
  }

  public static Long decode(String id) {
    return requireNonNull(stringIds, "string id encoder is null")
      .decode(id);
  }
}
