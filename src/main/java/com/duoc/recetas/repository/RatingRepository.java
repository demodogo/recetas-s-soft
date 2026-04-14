package com.duoc.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.duoc.recetas.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
  @Query("SELECT AVG(r.score) FROM Rating r WHERE r.recipe.id = :recipeId")
  Double getAverageRating(Long recipeId);
}
