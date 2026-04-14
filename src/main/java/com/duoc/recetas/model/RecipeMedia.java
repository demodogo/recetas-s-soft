package com.duoc.recetas.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "recipe_media")
public class RecipeMedia {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "recipe_id", nullable = false, foreignKey = @ForeignKey(name = "fk_recipe_media"))
  private Recipe recipe;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private MediaType type;

  @Column(nullable = false, length = 1000)
  private String url;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  public RecipeMedia() {}

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

  public MediaType getType() {
    return this.type;
  }

  public void setType(MediaType type) {
    this.type = type;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }
}
