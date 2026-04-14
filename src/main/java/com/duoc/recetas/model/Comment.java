package com.duoc.recetas.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JsonIgnore
  @JoinColumn(name = "recipe_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comment_recipe"))
  private Recipe recipe;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comment_user"))
  private User user;

  @Column(nullable = false, length = 2000)
  private String content;
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  public Comment() {}

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDateTime.now();
  }

  public Long getId() {
    return this.id;
  }

  public Recipe getRecipe() {
    return this.recipe;
  }

  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }
}
