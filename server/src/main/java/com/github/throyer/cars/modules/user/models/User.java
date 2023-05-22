package com.github.throyer.cars.modules.user.models;

import static com.github.throyer.cars.modules.shared.utils.PasswordUtils.encode;
import static jakarta.persistence.CascadeType.DETACH;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.List;

import com.github.throyer.cars.modules.role.model.Role;
import com.github.throyer.cars.modules.shared.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "user")
@Table(name = "user")
public class User extends BaseEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
    
  @Column(name = "name", length = 100)
  private String name;

  @Column(name = "email", unique = true, length = 100)
  private String email;

  @Column(name = "password", length = 256)
  private String password;

  @ManyToMany(cascade = DETACH, fetch = LAZY)
  @JoinTable(name = "user_role",
    joinColumns = {@JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id")})
  private List<Role> roles;

  public User(String name, String email, String password, List<Role> roles) {
    this.name = name;
    this.email = email;
    this.password = encode(password);
    this.roles = roles;
  }
}
