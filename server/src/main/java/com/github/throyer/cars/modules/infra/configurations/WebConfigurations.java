package com.github.throyer.cars.modules.infra.configurations;

import com.github.throyer.cars.modules.infra.environments.SecurityProperties;
import com.github.throyer.cars.modules.shared.ids.StringIds;
import com.github.throyer.cars.modules.shared.ids.StringIDWithHashIds;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.github.throyer.cars.modules.infra.constants.SecurityConstants.HASH_LENGTH;

@Configuration
public class WebConfigurations {
  @Bean
  StringIds ids(SecurityProperties properties) {
    return new StringIDWithHashIds(properties);
  }
  
  @Bean
  BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder(HASH_LENGTH);
  }
}
