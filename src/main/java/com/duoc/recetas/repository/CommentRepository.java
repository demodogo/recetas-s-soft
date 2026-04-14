package com.duoc.recetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.recetas.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByRecipe_Id(Long recipeId);
}
