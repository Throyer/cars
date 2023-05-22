package com.github.throyer.cars.modules.shared.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
public class PasswordUtils {
  private static BCryptPasswordEncoder encoder;

  public PasswordUtils(BCryptPasswordEncoder encoder) {
    PasswordUtils.encoder = encoder;
  }

  public static String encode(String password) {
    return requireNonNull(encoder, "string id encoder is null")
      .encode(password);
  }
  
  public static Boolean matches(String raw, String password) {
    return requireNonNull(encoder, "string id encoder is null")
      .matches(raw, password); 
  }
}
