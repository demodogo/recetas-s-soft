package com.duoc.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.recetas.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
