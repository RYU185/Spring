package com.dw.dynamic.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class IllegalArgumentException extends RuntimeException {
  public IllegalArgumentException() {
    super();
  }
  public IllegalArgumentException(String message) {
    super(message);
  }
}
