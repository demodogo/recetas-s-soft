package com.duoc.recetas.controller;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.recetas.dto.MediaRequest;
import com.duoc.recetas.model.Recipe;
import com.duoc.recetas.model.RecipeMedia;
import com.duoc.recetas.model.User;
import com.duoc.recetas.repository.RecipeMediaRepository;
import com.duoc.recetas.repository.RecipeRepository;
import com.duoc.recetas.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api")
public class MediaController {

  private final UserRepository userRepository;
  private final RecipeRepository recipeRepository;
  private final RecipeMediaRepository mediaRepository;

  public MediaController(UserRepository userRepository, RecipeRepository recipeRepository, RecipeMediaRepository mediaRepository) {
    this.userRepository = userRepository;
    this.recipeRepository = recipeRepository;
    this.mediaRepository = mediaRepository;
  }
  
  @PostMapping("/recipes/{id}/media")
  public ResponseEntity<?> addMedia(@PathVariable Long id, @RequestBody MediaRequest request) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String email = auth.getName();
    User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException());

    Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

    if (recipe.getUser().getId() != user.getId()) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para agregar media a esta receta");
    }
    
    RecipeMedia media = new RecipeMedia();

    media.setRecipe(recipe);
    media.setUrl(request.getUrl());
    media.setType(request.getType());

    mediaRepository.save(media);

    return ResponseEntity.ok("Media agregada");
  }
}
