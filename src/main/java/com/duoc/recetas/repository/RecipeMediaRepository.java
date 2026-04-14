package com.duoc.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.recetas.model.RecipeMedia;

public interface RecipeMediaRepository extends JpaRepository<RecipeMedia, Long> {
}
