package com.github.throyer.cars.modules.user.controllers;

import com.github.throyer.cars.modules.shared.swagger.BadRequestResponse;
import com.github.throyer.cars.modules.user.dtos.CreateUserData;
import com.github.throyer.cars.modules.user.dtos.UserInformation;
import com.github.throyer.cars.modules.user.services.CreateUserService;
import com.github.throyer.cars.modules.user.swagger.CreateUserConflictResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.github.throyer.cars.modules.infra.constants.LoggingConstants.SIMPLE_LOGGING;
import static com.github.throyer.cars.modules.infra.http.Responses.created;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@Tag(name = "Users")
@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class CreateUserController {
  private final CreateUserService service;  
  @PostMapping
  @ResponseStatus(CREATED)
  @ApiResponse(
    responseCode = "400",
    description = "when user creation occurs with invalid fields.",
    content = {@Content(schema = @Schema(implementation = BadRequestResponse.class))}
  )
  @ApiResponse(
    responseCode = "409",
    description = "Email already used",
    content = {@Content(schema = @Schema(implementation = CreateUserConflictResponse.class))}
  )
  @Operation(summary = "Register a new user", description = "Creates a new user")
  public ResponseEntity<UserInformation> create(@RequestBody @Valid CreateUserData data) {
    log.info(SIMPLE_LOGGING, "creating a new user.");
    var user = service.create(data);
    return created(new UserInformation(user));
  }
}
