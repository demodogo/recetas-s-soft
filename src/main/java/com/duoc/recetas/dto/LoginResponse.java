package com.duoc.recetas.dto;

public class LoginResponse {
  
  private String token;
  private String type = "Bearer";

  public LoginResponse() {}

  public LoginResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getType() {
    return this.type;
  }
}
