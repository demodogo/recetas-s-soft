package com.duoc.recetas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.duoc.recetas.model.Comment;
import com.duoc.recetas.model.Rating;
import com.duoc.recetas.model.Recipe;
import com.duoc.recetas.model.RecipeMedia;
import com.duoc.recetas.repository.CommentRepository;
import com.duoc.recetas.repository.RatingRepository;
import com.duoc.recetas.repository.RecipeMediaRepository;
import com.duoc.recetas.repository.RecipeRepository;

import jakarta.persistence.EntityNotFoundException;


@Controller
public class ViewController {
  
  private final RecipeRepository recipeRepository;
  private final CommentRepository commentRepository;
  private final RecipeMediaRepository recipeMediaRepository;
  private final RatingRepository ratingRepository;

  public ViewController(
    RecipeRepository recipeRepository,
    CommentRepository commentRepository,
    RecipeMediaRepository recipeMediaRepository,
    RatingRepository ratingRepository
  ) {
    this.recipeRepository = recipeRepository;
    this.commentRepository = commentRepository;
    this.recipeMediaRepository = recipeMediaRepository;
    this.ratingRepository = ratingRepository;
  }
  
  @GetMapping("/")
  public String index() {
      return "redirect:/recetas";
  }
  
  @GetMapping("/recetas")
  public String home(Model model) {
    List<Recipe> recipes = recipeRepository.findAll();
    model.addAttribute("recipes", recipes);
    return "home";
  }
  
  @GetMapping("/recetas/{id}")
  public String detail(@PathVariable Long id, Model model) {
      Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
      List<Comment> comments = commentRepository.findByRecipe_Id(id);
      List<RecipeMedia> media = recipeMediaRepository.findByRecipe_Id(id);
      List<Rating> ratings = ratingRepository.findByRecipe_Id(id);
      Double avgRating = ratingRepository.getAverageRating(id);

      model.addAttribute("recipe", recipe);
      model.addAttribute("comments", comments);
      model.addAttribute("media", media);
      model.addAttribute("ratings", ratings);
      model.addAttribute("avgRating", avgRating);
      
      return "recipe-detail";
  }
  
}
