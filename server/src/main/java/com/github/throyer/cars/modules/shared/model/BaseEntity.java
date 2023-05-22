package com.github.throyer.cars.modules.shared.model;

import static com.github.throyer.cars.modules.shared.utils.IdUtils.decode;
import static com.github.throyer.cars.modules.shared.utils.IdUtils.encode;

public abstract class BaseEntity {
  public abstract Long getId();
  public abstract void setId(Long id);
  
  public void setId(String id) {
    this.setId(decode(id));
  }
  
  public String getHashId() {
    return encode(this.getId());
  }
}
