package com.duoc.recetas.dto;

import lombok.Data;

@Data
public class ShareRequest {
  
  private String message;

  public ShareRequest() {}

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
