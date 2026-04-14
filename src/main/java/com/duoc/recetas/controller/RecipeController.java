package com.duoc.recetas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.recetas.model.Comment;
import com.duoc.recetas.model.Rating;
import com.duoc.recetas.model.Recipe;
import com.duoc.recetas.model.RecipeMedia;
import com.duoc.recetas.repository.CommentRepository;
import com.duoc.recetas.repository.RatingRepository;
import com.duoc.recetas.repository.RecipeMediaRepository;
import com.duoc.recetas.repository.RecipeRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
  
  private final RecipeRepository recipeRepository;
  private final CommentRepository commentRepository;
  private final RecipeMediaRepository mediaRepository;
  private final RatingRepository ratingRepository;

  public RecipeController(
    RecipeRepository recipeRepository,
    CommentRepository commentRepository,
    RecipeMediaRepository mediaRepository,
    RatingRepository ratingRepository
  ) {
    this.recipeRepository = recipeRepository;
    this.commentRepository = commentRepository;
    this.mediaRepository = mediaRepository;
    this.ratingRepository = ratingRepository;
  }

  @GetMapping
  public ResponseEntity<List<Recipe>> getAllRecipes() {
      return ResponseEntity.ok(recipeRepository.findAll());
  }

  @GetMapping("/{recipeId}")
  public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
      Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Receta no encontrada"));
      return ResponseEntity.ok(recipe);
  }

  @GetMapping("/{recipeId}/comments")
  public ResponseEntity<List<Comment>> getComments(@PathVariable Long recipeId) {
    if (!recipeRepository.existsById(recipeId)) {
      throw new EntityNotFoundException();
    }
    return ResponseEntity.ok(commentRepository.findByRecipe_Id(recipeId));
  }

  @GetMapping("/{recipeId}/media")
  public ResponseEntity<List<RecipeMedia>> getRecipeMedia(@PathVariable Long recipeId) {
      if (!recipeRepository.existsById(recipeId)) {
        throw new EntityNotFoundException();
      }
      return ResponseEntity.ok(mediaRepository.findByRecipe_Id(recipeId));
  }

  @GetMapping("/{recipeId}/ratings")
  public ResponseEntity<List<Rating>> getRatings(@PathVariable Long recipeId) {
    if (!recipeRepository.existsById(recipeId)) {
      throw new EntityNotFoundException();
    }
    return ResponseEntity.ok(ratingRepository.findByRecipe_Id(recipeId));
  }

  @GetMapping("/{recipeId}/ratings/average")
  public ResponseEntity<Map<String, Object>> getAvgRating(@PathVariable Long recipeId) {
      if (!recipeRepository.existsById(recipeId)) {
        throw new EntityNotFoundException();
      }

      Double avg = ratingRepository.getAverageRating(recipeId);
      
      return ResponseEntity.ok(
        Map.of(
          "recipeId", recipeId, "average", avg != null ? avg : 0
        )
      );
  }
  
  
  
  
}
