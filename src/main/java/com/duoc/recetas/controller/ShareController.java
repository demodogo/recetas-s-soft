package com.duoc.recetas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.recetas.dto.ShareRequest;
import com.duoc.recetas.dto.ShareResponse;
import com.duoc.recetas.model.Recipe;
import com.duoc.recetas.repository.RecipeRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api")
public class ShareController {
  
  private final RecipeRepository recipeRepository;

  public ShareController(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @PostMapping("/recipes/{id}/share")
  public ResponseEntity<ShareResponse> shareRecipe(@PathVariable Long id, @RequestBody ShareRequest request) {
    Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    String message =    request.getMessage();
    String recipeTitle = recipe.getTitle();
    String recipeDescription = recipe.getDescription();
    return ResponseEntity.ok(new ShareResponse(message, recipeTitle, recipeDescription));
  }
}
