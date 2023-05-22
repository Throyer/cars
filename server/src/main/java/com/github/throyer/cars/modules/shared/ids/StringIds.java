package com.github.throyer.cars.modules.shared.ids;

public interface StringIds {
  String encode(Long id);
  Long decode(String id);
}
