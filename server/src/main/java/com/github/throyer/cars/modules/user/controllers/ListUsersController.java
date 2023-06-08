package com.github.throyer.cars.modules.user.controllers;

import com.github.throyer.cars.modules.pagination.Page;
import com.github.throyer.cars.modules.shared.swagger.BadRequestResponse;
import com.github.throyer.cars.modules.user.dtos.UserInformation;
import com.github.throyer.cars.modules.user.services.FindAllUsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.github.throyer.cars.modules.infra.constants.LoggingConstants.SIMPLE_LOGGING;
import static com.github.throyer.cars.modules.infra.http.Responses.ok;

@Slf4j
@Tag(name = "Users")
@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class ListUsersController {
  
  private final FindAllUsersService service;
  
  @GetMapping
  @Operation(summary = "Returns a list of users")
  @ApiResponse(responseCode = "400", description = "Bad Request", content = {
    @Content(schema = @Schema(implementation = BadRequestResponse.class))
  })
  public ResponseEntity<Page<UserInformation>> index(
    @Parameter(example = "0")
    @RequestParam(name = "page", required = false)
    Integer page,
    
    @Parameter(example = "10")
    @RequestParam(name = "size", required = false)
    Integer size
  ) {
    log.info(SIMPLE_LOGGING, "listing users.");
    var response = service.find(page, size);
    return ok(response.map(UserInformation::new));
  }
}
