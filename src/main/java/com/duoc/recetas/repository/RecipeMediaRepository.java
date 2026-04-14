package com.duoc.recetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.recetas.model.RecipeMedia;

public interface RecipeMediaRepository extends JpaRepository<RecipeMedia, Long> {
  List<RecipeMedia> findByRecipe_Id(Long recipeId);
}
