package com.github.throyer.cars.modules.user.controllers;

import com.github.throyer.cars.modules.user.dtos.CreateUserData;
import com.github.throyer.cars.modules.user.dtos.UserInformation;
import com.github.throyer.cars.modules.user.services.CreateUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.github.throyer.cars.modules.infra.http.Responses.created;
import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "Users")
@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class CreateUserController {
  private final CreateUserService service;  
  @PostMapping
  @ResponseStatus(CREATED)
  @Operation(summary = "Register a new user", description = "Returns the new user")
  public ResponseEntity<UserInformation> create(@RequestBody CreateUserData data) {
    var user = service.create(data);
    return created(new UserInformation(user));
  }
}
