package com.github.throyer.cars.modules.user.models;

import com.github.throyer.cars.modules.role.model.Role;
import com.github.throyer.cars.modules.shared.model.BaseEntity;
import com.github.throyer.cars.modules.user.dtos.CreateUserData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.github.throyer.cars.modules.shared.utils.PasswordUtils.encode;
import static jakarta.persistence.CascadeType.DETACH;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

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

  public static User create(CreateUserData data, List<Role> roles) {
    var user = new User();
    
    user.setName(data.getName());
    user.setEmail(data.getEmail());
    user.setPassword(encode(data.getPassword()));
        
    user.setRoles(roles);
    return user;
  }
}
