package com.github.throyer.cars.modules.role.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity(name = "role")
@Table(name = "role")
public class Role implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  
  @Column(name = "name", unique = true, length = 100)
  private String name;
  
  @Override
  public String getAuthority() {
    return this.name;
  }
}
