package com.duoc.recetas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.recetas.dto.RatingRequest;
import com.duoc.recetas.model.Rating;
import com.duoc.recetas.model.Recipe;
import com.duoc.recetas.model.User;
import com.duoc.recetas.repository.RatingRepository;
import com.duoc.recetas.repository.RecipeRepository;
import com.duoc.recetas.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class RatingController {
  
  private final UserRepository userRepository;
  private final RecipeRepository recipeRepository;
  private final RatingRepository ratingRepository;

  public RatingController(UserRepository userRepository, RecipeRepository recipeRepository, RatingRepository ratingRepository) {
    this.userRepository = userRepository;
    this.recipeRepository = recipeRepository;
    this.ratingRepository = ratingRepository;
  }

  @PostMapping("/recipes/{id}/ratings")
  public ResponseEntity<?> rateRecipe(@PathVariable Long id, @RequestBody RatingRequest request) {
      
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String email = auth.getName();
      User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException());
      Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

      Rating rating = new Rating();
      rating.setUser(user);
      rating.setRecipe(recipe);
      rating.setScore(request.getScore());

      ratingRepository.save(rating);

      return ResponseEntity.ok("Rating guardadoi");
  }
  

  
}
