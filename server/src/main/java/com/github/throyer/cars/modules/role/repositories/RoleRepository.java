package com.github.throyer.cars.modules.role.repositories;

import com.github.throyer.cars.modules.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
  List<Role> findByNameIn(List<String> names);
}
