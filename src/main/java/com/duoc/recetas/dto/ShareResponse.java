package com.duoc.recetas.dto;

public class ShareResponse {
  private String message;
  private String recipeTitle;
  private String recipeDescription;

  public ShareResponse() {}

  public ShareResponse(String message, String recipeTitle, String recipeDescription) {
    this.message = message;
    this.recipeTitle = recipeTitle;
    this.recipeDescription = recipeDescription;
  }

  public String getMessage() {
    return this.message;
  }

  public String getRecipeTitle() {
    return this.recipeTitle;
  }

  public String getRecipeDescription() {
    return this.recipeDescription;
  }
}
