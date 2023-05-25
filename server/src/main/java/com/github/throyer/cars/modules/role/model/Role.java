package com.github.throyer.cars.modules.role.model;

import com.github.throyer.cars.modules.shared.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity(name = "role")
@Table(name = "role")
@NoArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  
  @Column(name = "name", unique = true, length = 100)
  private String name;
  
  @Override
  public String getAuthority() {
    return this.name;
  }

  public Role(String name) {
    this.name = name;
  }
}
