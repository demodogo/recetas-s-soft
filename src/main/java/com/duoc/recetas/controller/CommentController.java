package com.duoc.recetas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.recetas.dto.CommentRequest;
import com.duoc.recetas.model.Comment;
import com.duoc.recetas.model.Recipe;
import com.duoc.recetas.model.User;
import com.duoc.recetas.repository.CommentRepository;
import com.duoc.recetas.repository.RecipeRepository;
import com.duoc.recetas.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class CommentController {

  private final UserRepository userRepository;
  private final RecipeRepository recipeRepository;
  private final CommentRepository commentRepository;

  public CommentController(UserRepository userRepository, RecipeRepository recipeRepository, CommentRepository commentRepository) {
    this.userRepository = userRepository;
    this.recipeRepository = recipeRepository;
    this.commentRepository = commentRepository;
  }
  
  
  @PostMapping("/recipes/{id}/comments")
  public ResponseEntity<?> createComment(@PathVariable Long id, @RequestBody CommentRequest request) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String email = auth.getName();

    User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException());
    Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

    Comment comment = new Comment();
    comment.setUser(user);
    comment.setRecipe(recipe);
    comment.setContent(request.getContent());

    commentRepository.save(comment);
    return ResponseEntity.ok("Comentario agregado");
  }
  
}
