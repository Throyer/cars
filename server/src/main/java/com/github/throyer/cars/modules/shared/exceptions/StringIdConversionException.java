package com.github.throyer.cars.modules.shared.exceptions;

public class StringIdConversionException extends RuntimeException {
  public StringIdConversionException() {
  }

  public StringIdConversionException(String message) {
    super(message);
  }

  public StringIdConversionException(String message, Throwable cause) {
    super(message, cause);
  }

  public StringIdConversionException(Throwable cause) {
    super(cause);
  }

  public StringIdConversionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
