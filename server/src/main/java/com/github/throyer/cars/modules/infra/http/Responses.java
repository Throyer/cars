package com.github.throyer.cars.modules.infra.http;

import com.github.throyer.cars.modules.shared.errors.ApiError;
import com.github.throyer.cars.modules.shared.utils.JSON;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static java.lang.String.format;
import static java.net.URI.create;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

/**
 * HTTP Responses.
 * <p>
 * Classe util para simplificar a geração
 * de responses para status codes comuns
 * utilizando <code>ResponseEntity</code>.
 * </p>
 */
@Log4j2
public class Responses {

  private Responses() {
  }

  public static <T> ResponseEntity<T> forbidden(T body) {
    return status(403).body(body);
  }

  public static <T> ResponseEntity<T> forbidden() {
    return status(403).build();
  }

  public static <T> ResponseEntity<T> unauthorized(T body) {
    return status(401).body(body);
  }

  public static <T> ResponseEntity<T> unauthorized() {
    return status(401).build();
  }

  public static <T> ResponseEntity<T> ok(T body) {
    return ResponseEntity.ok(body);
  }

  public static <T> ResponseEntity<T> ok() {
    return ResponseEntity.ok()
      .build();
  }

  public static <T> ResponseEntity<T> notFound() {
    return ResponseEntity.notFound()
      .build();
  }

  public static <T> ResponseEntity<T> badRequest(T body) {
    return ResponseEntity.badRequest()
      .body(body);
  }

  public static <T> ResponseEntity<T> badRequest() {
    return ResponseEntity.badRequest()
      .build();
  }

  public static <T> ResponseEntity<T> noContent() {
    return ResponseEntity.noContent()
      .build();
  }

  public static <T> ResponseEntity<T> noContent(T entity, CrudRepository<T, ?> repository) {
    repository.delete(entity);
    return ResponseEntity
      .noContent()
      .build();
  }

  public static <T> ResponseEntity<T> created(T body, String location, String id) {
    return ResponseEntity.created(create(format("/%s/%s", location, id)))
      .body(body);
  }

  public static <T> ResponseEntity<T> created(T body) {
    return status(CREATED)
      .body(body);
  }

  public static ResponseStatusException conflict(String reason) {
    return new ResponseStatusException(CONFLICT, reason);
  }

  public static ResponseStatusException forbidden(String reason) {
    return new ResponseStatusException(FORBIDDEN, reason);
  }

  public static ResponseStatusException unauthorized(String reason) {
    return new ResponseStatusException(UNAUTHORIZED, reason);
  }

  public static ResponseStatusException notFound(String reason) {
    return new ResponseStatusException(NOT_FOUND, reason);
  }

  public static ResponseStatusException InternalServerError(String reason) {
    return new ResponseStatusException(INTERNAL_SERVER_ERROR, reason);
  }

  public static ResponseEntity<ApiError> fromException(ResponseStatusException exception) {
    return status(exception.getStatusCode())
      .body(new ApiError(exception.getReason(), exception.getStatusCode()));
  }

  public static void forbidden(HttpServletResponse response) {
    if (response.getStatus() != 200) {
      return;
    }

    try {
      response.setStatus(FORBIDDEN.value());
      response.setContentType("application/json");
      response.getWriter().write(JSON.stringify(
        new ApiError("Can't find bearer token on Authorization header.", FORBIDDEN)));
    } catch (Exception exception) {
      log.error("error writing forbidden response");
    }
  }

  public static void expired(HttpServletResponse response) {
    if (response.getStatus() != 200) {
      return;
    }

    try {
      response.setStatus(FORBIDDEN.value());
      response.setContentType("application/json");
      response.getWriter().write(JSON.stringify(
        new ApiError("Token expired or invalid.", FORBIDDEN)));
    } catch (IOException exception) {
      log.error("error writing token expired/invalid response");
    }
  }
}