package com.hussain.visaapp.model;

import java.time.LocalDateTime;

public class VisaHelloWorldResponse {

  private LocalDateTime timestamp;

  private String message;

  @SuppressWarnings("unused")
  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(final LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }
}
