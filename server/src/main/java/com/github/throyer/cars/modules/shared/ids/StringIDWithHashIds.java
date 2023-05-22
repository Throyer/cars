package com.github.throyer.cars.modules.shared.ids;

import com.github.throyer.cars.modules.infra.environments.SecurityProperties;
import com.github.throyer.cars.modules.shared.exceptions.StringIdConversionException;
import org.hashids.Hashids;

import static com.github.throyer.cars.modules.infra.constants.SecurityConstants.HASH_LENGTH;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;

public class StringIDWithHashIds implements StringIds {
  private final Hashids hashids;

  public StringIDWithHashIds(SecurityProperties properties) {
    this.hashids = new Hashids(properties.getHashidSecret(), HASH_LENGTH);;
  }

  @Override
  public String encode(Long id) {
    try {
      return this.hashids.encode(requireNonNull(id, "id is null"));  
    } catch (Exception exception) {
      throw new StringIdConversionException("error on id encoding", exception); 
    }
  }

  @Override
  public Long decode(String id) {
    try {
      return stream(this.hashids.decode(requireNonNull(id, "id is null")))
        .boxed()
        .findFirst()
        .orElse(null);
    } catch (Exception exception) {
      throw new StringIdConversionException("error on id encoding", exception);
    }
  }
}
