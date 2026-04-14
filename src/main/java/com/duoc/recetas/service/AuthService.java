package com.duoc.recetas.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.duoc.recetas.dto.LoginRequest;

@Service
public class AuthService {
  
  private final AuthenticationManager authManager;

  public AuthService(AuthenticationManager authManager) {
    this.authManager = authManager;
  }

  public Authentication authenticate(LoginRequest request) {
    return authManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
    );
  }
}
