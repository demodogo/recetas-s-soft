package com.duoc.recetas.model;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = {
  @UniqueConstraint(name = "uk_user_email", columnNames = "email")
})
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 120)
  private String fullName;

  @Column(nullable = false, length = 120)
  private String email;

  @JsonIgnore
  @Column(nullable = false, length = 120)
  private String password;

  @Column(nullable = false)
  private Boolean enabled;

  @Column(nullable = false, updatable =  false)
  @CreatedDate
  private LocalDate createdAt;

  public User() {}

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDate.now();
  }

  public Long getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }
}
